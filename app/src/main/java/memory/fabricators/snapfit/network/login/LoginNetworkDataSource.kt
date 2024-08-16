package memory.fabricators.snapfit.network.login

import memory.fabricators.snapfit.network.login.model.TokenResponse

abstract class LoginNetworkDataSource {
    abstract fun reissueToken(): TokenResponse
}
