package memory.fabricators.snapfit.data.login

import memory.fabricators.snapfit.network.login.LoginNetworkDataSource

class LoginRepositoryImpl(
    private val loginNetworkDataSource: LoginNetworkDataSource,
) : LoginRepository() {
    override suspend fun login(token: String) {
        loginNetworkDataSource.login(token)
    }
}
