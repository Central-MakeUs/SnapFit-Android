package memory.fabricators.snapfit.network.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val id: Int,
    @SerialName("nickName") val nickname: String,
    val vibes: List<VibeResponse>,
    val socialType: String,
    val profile: String,
    val marketingReceive: Boolean,
    val photographer: Boolean,
    val noti: Boolean,
) {
    @Serializable
    data class VibeResponse(
        val id: Int,
        val name: String,
    )
}