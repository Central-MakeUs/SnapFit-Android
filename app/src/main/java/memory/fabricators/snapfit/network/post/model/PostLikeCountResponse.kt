package memory.fabricators.snapfit.network.post.model

import kotlinx.serialization.Serializable

@Serializable
data class PostLikeCountResponse(
    val count: Int,
)
