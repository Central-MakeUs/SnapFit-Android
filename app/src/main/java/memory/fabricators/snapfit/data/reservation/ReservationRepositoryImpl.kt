package memory.fabricators.snapfit.data.reservation

import memory.fabricators.snapfit.data.reservation.model.ReservationDetails
import memory.fabricators.snapfit.data.reservation.model.ReservationList
import memory.fabricators.snapfit.network.reservation.ReservationNetworkDataSource
import memory.fabricators.snapfit.network.reservation.model.ReservationRequest

class ReservationRepositoryImpl(
    private val reservationNetworkDataSource: ReservationNetworkDataSource,
) : ReservationRepository() {
    override suspend fun createReservation(
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
    ): ReservationDetails {
        val response = reservationNetworkDataSource.createReservation(
            reservationRequest = ReservationRequest(
                email = email,
                phoneNumber = phoneNumber,
                postId = postId,
                minutes = minutes,
                price = price,
                person = person,
                personPrice = personPrice,
                reservationLocation = reservationLocation,
                reservationTime = reservationTime,
                makerId = makerId,
            )
        )
        return with(response) {
            ReservationDetails(
                id = id,
                user = ReservationDetails.User(
                    id = user.id,
                    nickName = user.nickName,
                ),
                post = ReservationDetails.Post(
                    id = post.id,
                    maker = ReservationDetails.Maker(
                        id = maker.id,
                        nickName = maker.nickName,
                    ),
                    title = post.title,
                    thumbNail = post.thumbNail,
                    vibes = post.vibes,
                    locations = post.locations,
                    price = post.price,
                    studio = post.studio,
                    like = post.like,
                ),
                reservationTime = response.reservationTime,
                reservationLocation = response.reservationLocation,
                person = response.person,
                personPrice = response.personPrice,
                basePrice = basePrice,
                totalPrice = totalPrice,
                cancelMessage = cancelMessage,
                maker = ReservationDetails.Maker(
                    id = maker.id,
                    nickName = maker.nickName,
                ),
                email = response.email,
                phoneNumber = response.phoneNumber,
            )
        }
    }

    override suspend fun getReservationDetails(reservationId: Long): ReservationDetails {
        val response = reservationNetworkDataSource.getReservationDetails(reservationId.toString())
        return with(response) {
            ReservationDetails(
                id = id,
                user = ReservationDetails.User(
                    id = user.id,
                    nickName = user.nickName,
                ),
                post = ReservationDetails.Post(
                    id = post.id,
                    maker = ReservationDetails.Maker(
                        id = post.maker.id,
                        nickName = post.maker.nickName,
                    ),
                    title = post.title,
                    thumbNail = post.thumbNail,
                    vibes = post.vibes,
                    locations = post.locations,
                    price = post.price,
                    studio = post.studio,
                    like = post.like,
                ),
                maker = ReservationDetails.Maker(
                    id = maker.id,
                    nickName = maker.nickName,
                ),
                reservationTime = reservationTime,
                reservationLocation = reservationLocation,
                person = person,
                personPrice = personPrice,
                basePrice = basePrice,
                totalPrice = totalPrice,
                cancelMessage = cancelMessage,
                email = email,
                phoneNumber = phoneNumber,
            )
        }
    }

    override suspend fun getReservations(
        offset: Int,
        limit: Int,
    ): ReservationList {
        val response = reservationNetworkDataSource.getUserReservations(
            offset = offset,
            limit = limit,
        )
        return ReservationList(
            offset = response.offset,
            limit = response.limit,
            data = response.data.map {
                ReservationList.ReservationListItem(
                    id = it.id,
                    reservationTime = it.reservationTime,
                    post = ReservationList.Post(
                        id = it.post.id,
                        maker = ReservationList.Maker(
                            id = it.post.maker.id,
                            nickName = it.post.maker.nickName,
                        ),
                        title = it.post.title,
                        vibes = it.post.vibes,
                        locations = it.post.locations,
                        thumbNail = it.post.thumbNail,
                        price = it.post.price,
                        studio = it.post.studio,
                        like = it.post.like,
                    ),
                    totalPrice = it.totalPrice,
                    cancelMessage = it.cancelMessage,
                )
            },
        )
    }

    override suspend fun getReservationCount(): Int =
        reservationNetworkDataSource.getReservationCount().count
}
