package memory.fabricators.snapfit.data.user.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Int,
    val nickname: String,
    val vibes: List<Vibe>,
    val socialType: String,
    val profile: String?,
    val marketingReceive: Boolean,
    val photographer: Boolean,
    val notificationEnabled: Boolean,
)
