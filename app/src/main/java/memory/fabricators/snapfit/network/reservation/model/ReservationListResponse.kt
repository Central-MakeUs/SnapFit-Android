package memory.fabricators.snapfit.network.reservation.model

import kotlinx.serialization.Serializable

@Serializable
data class ReservationListResponse(
    val offset: Int,
    val limit: Int,
    val data: List<ReservationListItem>,
) {
    @Serializable
    data class ReservationListItem(
        val id: Long,
        val reservationTime: String,
        val post: PostResponse,
        val totalPrice: Long,
        val cancelMessage: String,
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

    @Serializable
    data class MakerResponse(
        val id: Long,
        val nickName: String,
    )
}
