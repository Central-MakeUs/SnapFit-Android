package memory.fabricators.snapfit.ui.reservation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.data.reservation.ReservationRepository
import memory.fabricators.snapfit.data.reservation.model.ReservationList
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ReservationListViewModel(
    private val reservationRepository: ReservationRepository,
) : ViewModel(),
    ContainerHost<ReservationListState, Unit> {
    override val container = container<ReservationListState, Unit>(ReservationListState())

    init {
        fetchReservationList()
    }

    private fun fetchReservationList() = intent {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                reservationRepository.getReservations(
                    offset = 0,
                    limit = 20,
                )
            }.onSuccess {
                reduce {
                    state.copy(reservationList = it.data)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}

data class ReservationListState(
    val reservationList: List<ReservationList.ReservationListItem>? = null,
)
