package memory.fabricators.snapfit.data.user

import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.datastore.user.UserDataStoreDataSource
import memory.fabricators.snapfit.network.user.UserNetworkDataSource

class UserRepositoryImpl(
    private val userDataStoreDataSource: UserDataStoreDataSource,
    private val userNetworkDataSource: UserNetworkDataSource,
) : UserRepository() {
    override suspend fun fetchVibes(): List<Vibe> {
        return userNetworkDataSource.fetchVibes()
    }
}
