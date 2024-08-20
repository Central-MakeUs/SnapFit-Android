package memory.fabricators.snapfit.network.login

import memory.fabricators.snapfit.network.login.model.SocialSignUpRequest
import memory.fabricators.snapfit.network.login.model.TokenResponse

abstract class LoginNetworkDataSource {
    abstract suspend fun login(token: String): TokenResponse
    abstract suspend fun socialSignUp(
        req: SocialSignUpRequest,
        socialAccessToken: String,
    ): TokenResponse
    abstract suspend fun reissueToken(): TokenResponse
}
