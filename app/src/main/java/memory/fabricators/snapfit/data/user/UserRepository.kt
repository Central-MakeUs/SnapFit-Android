package memory.fabricators.snapfit.data.user

import memory.fabricators.snapfit.data.user.model.Vibe

abstract class UserRepository {
    abstract suspend fun fetchVibes(): List<Vibe>
}
