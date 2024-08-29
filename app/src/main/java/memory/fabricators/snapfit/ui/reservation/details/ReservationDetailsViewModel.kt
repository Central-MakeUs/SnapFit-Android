package memory.fabricators.snapfit.ui.reservation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.reservation.ReservationRepository
import memory.fabricators.snapfit.data.reservation.model.ReservationDetails
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReservationDetailsViewModel(
    private val reservationRepository: ReservationRepository,
) : ViewModel(),
    ContainerHost<ReservationDetailsState, Unit> {
    override val container = container<ReservationDetailsState, Unit>(ReservationDetailsState())

    fun fetchReservationDetails(bookingId: Long) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                reservationRepository.getReservationDetails(bookingId)
            }.onSuccess {
                reduce {
                    state.copy(reservationDetails = it)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class ReservationDetailsState(
    val reservationDetails: ReservationDetails? = null,
)
