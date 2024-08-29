package memory.fabricators.snapfit.network.reservation

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import memory.fabricators.snapfit.core.token.TokenManager
import memory.fabricators.snapfit.network.reservation.model.ReservationCountResponse
import memory.fabricators.snapfit.network.reservation.model.ReservationDetailsResponse
import memory.fabricators.snapfit.network.reservation.model.ReservationListResponse
import memory.fabricators.snapfit.network.reservation.model.ReservationRequest

class ReservationNetworkDataSourceImpl(
    private val httpClient: HttpClient,
    private val tokenManager: TokenManager,
) : ReservationNetworkDataSource() {

    override suspend fun getReservationDetails(reservationId: String): ReservationDetailsResponse {
        val response = httpClient.get("/snapfit/reservation/$reservationId") {
            bearerAuth(token = tokenManager.accessToken)
        }
        return response.body()
    }

    override suspend fun createReservation(reservationRequest: ReservationRequest): ReservationDetailsResponse {
        val response = httpClient.post("/snapfit/reservation") {
            bearerAuth(token = tokenManager.accessToken)
            setBody(reservationRequest)
        }
        return response.body()
    }

    override suspend fun cancelReservation(reservationId: String) {
        httpClient.delete("/snapfit/reservation/$reservationId")
    }

    override suspend fun getReservationCount(): ReservationCountResponse {
        val response = httpClient.get("/snapfit/reservation/count") {}
        return response.body()
    }

    override suspend fun getMakerReservations(makerId: String): ReservationListResponse {
        val response = httpClient.get("/snapfit/reservation/maker/$makerId") {}
        return response.body()
    }

    override suspend fun getUserReservations(userId: String): ReservationListResponse {
        val response = httpClient.get("/snapfit/reservation/user/$userId") {}
        return response.body()
    }
}
