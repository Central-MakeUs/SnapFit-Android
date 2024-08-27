package memory.fabricators.snapfit.data.reservation.di

import memory.fabricators.snapfit.data.reservation.ReservationRepository
import memory.fabricators.snapfit.data.reservation.ReservationRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val reservationDataModule: Module
    get() = module {
        single<ReservationRepository> {
            ReservationRepositoryImpl(reservationNetworkDataSource = get())
        }
    }
