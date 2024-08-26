package memory.fabricators.snapfit.network.post.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDetailsResponse(
    val id: Long,
    val maker: MakerResponse,
    val createdAt: String,
    val thumbnail: String,
    val images: List<String>,
    val desc: String,
    val title: String,
    val vibes: List<String>,
    val locations: List<String>,
    val prices: List<PriceResponse>,
    val personPrice: Long,
    val studio: Boolean,
    val like: Boolean,
) {
    @Serializable
    data class MakerResponse(
        val id: Long,
        @SerialName("nickName") val nickname: String,
    )

    @Serializable
    data class PriceResponse(
        val min: Long,
        val max: Long,
    )
}
