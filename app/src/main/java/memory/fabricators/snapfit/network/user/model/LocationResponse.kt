package memory.fabricators.snapfit.network.user.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val id: Long,
    val adminName: String,
)
