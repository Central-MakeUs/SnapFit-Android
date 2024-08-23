package memory.fabricators.snapfit.data.user

import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.data.user.model.UserInfo
import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.network.login.LoginNetworkDataSource
import memory.fabricators.snapfit.network.login.model.SocialSignUpRequest
import memory.fabricators.snapfit.network.user.UserNetworkDataSource

class UserRepositoryImpl(
    private val tokenManager: TokenManager,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val loginNetworkDataSource: LoginNetworkDataSource,
) : UserRepository() {
    override suspend fun fetchUserInfo(): UserInfo {
        val response = userNetworkDataSource.getUserInfo()
        return with(response) {
            UserInfo(
                id = id,
                nickname = nickname,
                vibes = vibes.map {
                    Vibe(
                        id = it.id,
                        name = it.name,
                    )
                },
                socialType = socialType,
                profile = profile,
                marketingReceive = marketingReceive,
                photographer = photographer,
                notificationEnabled = noti,
            )
        }
    }

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
            println("TOKENTOKEN\n$accessToken\n$refreshToken")
            tokenManager.setTokens(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }
}
