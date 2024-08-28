package memory.fabricators.snapfit.ui.post.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.post.PostRepository
import memory.fabricators.snapfit.data.post.model.PostDetails
import memory.fabricators.snapfit.data.reservation.ReservationRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BookingViewModel(
    private val postRepository: PostRepository,
    private val reservationRepository: ReservationRepository,
) : ViewModel(), ContainerHost<BookingState, BookingSideEffect> {
    override val container = container<BookingState, BookingSideEffect>(BookingState())

    fun fetchPostDetails(postId: Long) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                postRepository.getPostDetails(postId)
            }.onSuccess {
                reduce {
                    state.copy(postDetails = it)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun createReservation(
        email: String,
        phoneNumber: String,
        postId: Long,
        makerId: Long,
        minutes: Long,
        price: Long,
        person: Long,
        personPrice: Long,
        reservationLocation: String,
        reservationTime: String,
    ) = intent {
        val date: String
        try {
            val (m, d, h) = reservationTime.split('-').map(String::toInt)
            val time = LocalDateTime.of(LocalDateTime.now().year, m, d, h, 0, 0, 0)
            date = time.format(DateTimeFormatter.ISO_DATE_TIME) + ".675Z"
        } catch (_: Exception) {
            postSideEffect(BookingSideEffect.CheckTimeFormat)
            return@intent
        }
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                reservationRepository.createReservation(
                    email = email,
                    phoneNumber = phoneNumber,
                    postId = postId,
                    makerId = makerId,
                    minutes = minutes,
                    price = price,
                    person = person,
                    personPrice = personPrice * person,
                    reservationLocation = reservationLocation,
                    reservationTime = date,
                )
            }.onSuccess {
                postSideEffect(BookingSideEffect.ReservationCreated(reservationId = it.id))
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class BookingState(
    val postDetails: PostDetails? = null,
)

sealed class BookingSideEffect {
    data object CheckTimeFormat : BookingSideEffect()
    class ReservationCreated(val reservationId: Long) : BookingSideEffect()
}
