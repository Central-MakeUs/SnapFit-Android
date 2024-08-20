package memory.fabricators.snapfit.network.post.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostListResponse(
    val offset: Int,
    val limit: Int,
    val data: List<PostResponse>,
) {
    @Serializable
    data class PostResponse(
        val id: Long,
        val maker: MakerResponse,
        val title: String,
        @SerialName("thumbNail") val thumbnail: String,
        val vibes: List<String>,
        val locations: List<String>,
        val price: Int,
        val studio: Boolean,
        val like: Boolean,
    ) {
        @Serializable
        data class MakerResponse(
            val id: Long,
            @SerialName("nickName") val nickname: String,
        )
    }
}
