package memory.fabricators.snapfit.ui.reservation.details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val reservationUiModule: Module
    get() = module {
        viewModel { ReservationDetailsViewModel() }
    }
