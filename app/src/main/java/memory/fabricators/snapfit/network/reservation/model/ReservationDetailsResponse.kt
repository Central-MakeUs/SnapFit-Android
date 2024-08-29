package memory.fabricators.snapfit.network.reservation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReservationDetailsResponse(
    val id: Long,
    val user: UserResponse,
    val maker: MakerResponse,
    val post: PostResponse,
    @SerialName("reservationTime") val reservationTime: String,
    val reservationLocation: String,
    val person: Long,
    val personPrice: Long,
    val basePrice: Long,
    val totalPrice: Long,
    val cancelMessage: String?,
) {
    @Serializable
    data class UserResponse(
        val id: Long,
        val nickName: String,
    )

    @Serializable
    data class MakerResponse(
        val id: Long,
        val nickName: String,
    )

    @Serializable
    data class PostResponse(
        val id: Long,
        val maker: MakerResponse,
        val title: String,
        val thumbNail: String,
        val vibes: List<String>,
        val locations: List<String>,
        val price: Long,
        val studio: Boolean,
        val like: Boolean,
    )
}
