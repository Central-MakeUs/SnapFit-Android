package memory.fabricators.snapfit.network.user

import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.network.login.model.TokenResponse
import memory.fabricators.snapfit.network.user.model.SignUpRequest

abstract class UserNetworkDataSource {
    abstract suspend fun fetchVibes(): List<Vibe>
    abstract suspend fun signUp(
        req: SignUpRequest,
        socialAccessToken: String,
    ): TokenResponse
}
