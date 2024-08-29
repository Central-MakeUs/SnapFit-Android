package memory.fabricators.snapfit.ui.reservation.list.di

import memory.fabricators.snapfit.ui.reservation.list.ReservationListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val reservationListUiModule: Module
    get() = module {
        viewModel { ReservationListViewModel(reservationRepository = get()) }
    }
