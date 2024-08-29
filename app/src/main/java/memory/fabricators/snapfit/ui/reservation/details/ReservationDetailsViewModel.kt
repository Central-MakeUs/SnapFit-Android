package memory.fabricators.snapfit.ui.reservation.details

import androidx.lifecycle.ViewModel
import memory.fabricators.snapfit.data.reservation.model.ReservationDetails
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class ReservationDetailsViewModel : ViewModel(),
    ContainerHost<ReservationDetailsState, Unit> {
    override val container = container<ReservationDetailsState, Unit>(ReservationDetailsState())


}

data class ReservationDetailsState(
    val reservationDetails: ReservationDetails? = null,
)
