package memory.fabricators.snapfit.data.auth

import memory.fabricators.snapfit.datastore.auth.AuthDataStoreDataSource
import memory.fabricators.snapfit.network.auth.AuthNetworkDataSource

class AuthRepositoryImpl(
    val authDataStoreDataSource: AuthDataStoreDataSource,
    val authNetworkDataSource: AuthNetworkDataSource,
) : AuthRepository() {
}
