package memory.fabricators.snapfit.data.reservation.model

data class ReservationList(
    val offset: Int,
    val limit: Int,
    val data: List<ReservationListItem>,
) {
    data class ReservationListItem(
        val id: Long,
        val reservationTime: String,
        val post: Post,
        val totalPrice: Long,
        val cancelMessage: String?,
    )

    data class Post(
        val id: Long,
        val maker: Maker,
        val title: String,
        val thumbNail: String,
        val vibes: List<String>,
        val locations: List<String>,
        val price: Long,
        val studio: Boolean,
        val like: Boolean,
    )

    data class Maker(
        val id: Long,
        val nickName: String,
    )
}
