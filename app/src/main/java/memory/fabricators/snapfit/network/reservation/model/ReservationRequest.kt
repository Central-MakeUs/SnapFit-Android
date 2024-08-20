package memory.fabricators.snapfit.network.reservation.model

import kotlinx.serialization.Serializable

@Serializable
data class ReservationRequest(
    val email: String,
    val phoneNumber: String,
    val postId: Long,
    val makerId: Long,
    val minutes: Long,
    val price: Long,
    val person: Long,
    val personPrice: Long,
    val reservationLocation: String,
    val reservationTime: String,
)
