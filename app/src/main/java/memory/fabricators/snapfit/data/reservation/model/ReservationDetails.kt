package memory.fabricators.snapfit.data.reservation.model

data class ReservationDetails(
    val id: Long,
    val user: User,
    val maker: Maker,
    val post: Post,
    val reservationTime: String,
    val reservationLocation: String,
    val person: Long,
    val personPrice: Long,
    val basePrice: Long,
    val totalPrice: Long,
    val cancelMessage: String,
) {
    data class User(
        val id: Long,
        val nickName: String,
    )

    data class Maker(
        val id: Long,
        val nickName: String,
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
}

