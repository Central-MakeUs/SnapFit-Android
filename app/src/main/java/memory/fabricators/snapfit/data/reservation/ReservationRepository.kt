package memory.fabricators.snapfit.data.reservation

import memory.fabricators.snapfit.data.reservation.model.ReservationDetails
import memory.fabricators.snapfit.data.reservation.model.ReservationList

abstract class ReservationRepository {
    abstract suspend fun createReservation(
        email: String,
        phoneNumber: String,
        postId: Long,
        makerId: Long,
        minutes: Long,
        price: Long,
        person: Long,
        personPrice: Long,
        reservationLocation: String,
        reservationTime: String,
    ): ReservationDetails

    abstract suspend fun getReservationDetails(reservationId: Long): ReservationDetails

    abstract suspend fun getReservations(offset: Int, limit: Int): ReservationList
}
