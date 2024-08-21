package memory.fabricators.snapfit.data.login

import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.network.login.LoginNetworkDataSource

class LoginRepositoryImpl(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val tokenManager: TokenManager,
) : LoginRepository() {
    override suspend fun login(token: String) {
        loginNetworkDataSource.login(token).also {
            tokenManager.setTokens(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken,
            )
        }
    }
}
