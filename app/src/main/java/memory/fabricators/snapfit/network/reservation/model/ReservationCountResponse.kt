package memory.fabricators.snapfit.network.reservation.model

import kotlinx.serialization.Serializable

@Serializable
data class ReservationCountResponse(
    val count: Int,
)
