package memory.fabricators.snapfit.network.reservation

import memory.fabricators.snapfit.network.reservation.model.ReservationCountResponse
import memory.fabricators.snapfit.network.reservation.model.ReservationDetailsResponse
import memory.fabricators.snapfit.network.reservation.model.ReservationListResponse
import memory.fabricators.snapfit.network.reservation.model.ReservationRequest

abstract class ReservationNetworkDataSource {
    abstract suspend fun getReservationDetails(reservationId: String): ReservationDetailsResponse
    abstract suspend fun createReservation(reservationRequest: ReservationRequest): ReservationDetailsResponse
    abstract suspend fun cancelReservation(reservationId: String)
    abstract suspend fun getReservationCount(): ReservationCountResponse
    abstract suspend fun getMakerReservations(makerId: String): ReservationListResponse
    abstract suspend fun getUserReservations(userId: String): ReservationListResponse
}
