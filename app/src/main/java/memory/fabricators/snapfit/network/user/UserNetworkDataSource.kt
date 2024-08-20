package memory.fabricators.snapfit.network.user

import memory.fabricators.snapfit.data.user.model.Vibe

abstract class UserNetworkDataSource {
    abstract suspend fun fetchVibes(): List<Vibe>
}
