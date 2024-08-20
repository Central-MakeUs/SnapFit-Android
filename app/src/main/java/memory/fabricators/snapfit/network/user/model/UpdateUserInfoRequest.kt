package memory.fabricators.snapfit.network.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserInfoRequest(
    @SerialName("nickName") val nickname: String,
    val vibes: List<String>,
)
