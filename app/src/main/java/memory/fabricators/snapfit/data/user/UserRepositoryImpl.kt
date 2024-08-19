package memory.fabricators.snapfit.data.user

import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.datastore.user.UserDataStoreDataSource
import memory.fabricators.snapfit.network.user.UserNetworkDataSource

class UserRepositoryImpl(
    private val tokenManager: TokenManager,
    private val userNetworkDataSource: UserNetworkDataSource,
) : UserRepository() {
    override suspend fun fetchVibes(): List<Vibe> {
        return userNetworkDataSource.fetchVibes()
    }

    override suspend fun signUp(
        social: String,
        vibes: List<String>,
        deviceType: String,
        deviceToken: String,
        nickname: String,
        marketing: Boolean,
    ) {
        val tokens = userNetworkDataSource.signUp()

    }
}
