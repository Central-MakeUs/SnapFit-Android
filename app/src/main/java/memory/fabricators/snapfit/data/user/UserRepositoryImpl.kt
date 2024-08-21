package memory.fabricators.snapfit.data.user

import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.network.login.LoginNetworkDataSource
import memory.fabricators.snapfit.network.login.model.SocialSignUpRequest
import memory.fabricators.snapfit.network.user.UserNetworkDataSource

class UserRepositoryImpl(
    private val tokenManager: TokenManager,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val loginNetworkDataSource: LoginNetworkDataSource,
) : UserRepository() {
    override suspend fun fetchVibes(): List<Vibe> {
        return userNetworkDataSource.fetchVibes()
    }

    override suspend fun signUp(
        social: String,
        socialAccessToken: String,
        vibes: List<String>,
        deviceType: String,
        deviceToken: String,
        nickname: String,
        marketing: Boolean,
    ) {
        loginNetworkDataSource.socialSignUp(
            socialAccessToken = socialAccessToken,
            req = SocialSignUpRequest(
                social = social,
                vibes = vibes,
                deviceType = deviceType,
                deviceToken = deviceToken,
                nickname = nickname,
                marketing = marketing,
            ),
        ).also { (accessToken, refreshToken) ->
            tokenManager.setTokens(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }
}
