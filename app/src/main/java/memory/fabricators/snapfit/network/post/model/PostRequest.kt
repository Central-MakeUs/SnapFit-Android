package memory.fabricators.snapfit.network.post.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val vibes: List<String>,
    val locations: List<String>,
    val imageNames: List<String>,
    @SerialName("thumbnail") val thumbnail: String,
    val title: String,
    val desc: String,
    val prices: List<PriceRequest>,
    val personPrice: Long,
    val studio: Boolean,
) {
    @Serializable
    data class PriceRequest(
        val min: Long,
        val price: Long,
    )
}
