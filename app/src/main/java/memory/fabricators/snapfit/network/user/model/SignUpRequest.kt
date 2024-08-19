package memory.fabricators.snapfit.network.user.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val social: String,
    val vibes: List<String>,
    val deviceType: String,
    val deviceToken: String,
    val nickname: String,
    val marketing: Boolean,
)
