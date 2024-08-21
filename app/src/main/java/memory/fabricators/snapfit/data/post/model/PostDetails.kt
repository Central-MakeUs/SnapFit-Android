package memory.fabricators.snapfit.data.post.model

data class PostDetails(
    val id: Long,
    val maker: Maker,
    val createdAt: String,
    val thumbnail: String,
    val images: String,
    val desc: String,
    val title: String,
    val vibes: List<String>,
    val locations: List<String>,
    val prices: List<PriceResponse>,
    val personPrice: Long,
    val studio: Boolean,
    val like: Boolean,
) {
    data class Maker(
        val id: Long,
        val nickname: String,
    )

    data class PriceResponse(
        val min: Long,
        val max: Long,
    )
}
