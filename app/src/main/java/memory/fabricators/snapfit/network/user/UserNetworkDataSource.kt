package memory.fabricators.snapfit.network.user

import memory.fabricators.snapfit.data.user.model.Vibe
import memory.fabricators.snapfit.network.user.model.LocationResponse
import memory.fabricators.snapfit.network.user.model.UpdateUserInfoRequest
import memory.fabricators.snapfit.network.user.model.UserInfoResponse

abstract class UserNetworkDataSource {
    abstract suspend fun getLocations(): List<LocationResponse>
    abstract suspend fun getUserInfo(): UserInfoResponse
    abstract suspend fun deleteUser()
    abstract suspend fun updateUserInfo(req: UpdateUserInfoRequest): UserInfoResponse
    abstract suspend fun fetchVibes(): List<Vibe>
}
