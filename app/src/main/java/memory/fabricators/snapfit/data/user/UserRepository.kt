package memory.fabricators.snapfit.data.user

import memory.fabricators.snapfit.data.user.model.UserInfo
import memory.fabricators.snapfit.data.user.model.Vibe

abstract class UserRepository {
    abstract suspend fun fetchUserInfo(): UserInfo
    abstract suspend fun fetchVibes(): List<Vibe>
    abstract suspend fun signUp(
        social: String,
        socialAccessToken: String,
        vibes: List<String>,
        deviceType: String,
        deviceToken: String,
        nickname: String,
        marketing: Boolean,
    )
}
