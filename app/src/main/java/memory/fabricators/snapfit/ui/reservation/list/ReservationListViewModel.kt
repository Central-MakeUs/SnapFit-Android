package memory.fabricators.snapfit.ui.reservation.list

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

class ReservationListViewModel: ViewModel(),
        ContainerHost<> {

        }

data class ReservationListState(
    val reservationList: List
)
