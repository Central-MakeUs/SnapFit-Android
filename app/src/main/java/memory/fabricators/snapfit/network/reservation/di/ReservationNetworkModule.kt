package memory.fabricators.snapfit.network.reservation.di

import memory.fabricators.snapfit.network.reservation.ReservationNetworkDataSource
import memory.fabricators.snapfit.network.reservation.ReservationNetworkDataSourceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val reservationNetworkModule: Module
    get() = module {
        single<ReservationNetworkDataSource> {
            ReservationNetworkDataSourceImpl(
                httpClient = get(),
                tokenManager = get(),
            )
        }
    }
