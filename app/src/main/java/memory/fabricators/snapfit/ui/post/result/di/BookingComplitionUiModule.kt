package memory.fabricators.snapfit.ui.post.result.di

import memory.fabricators.snapfit.ui.post.result.BookingCompletionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val bookingCompletionUiModule: Module
    get() = module {
        viewModel { BookingCompletionViewModel(reservationRepository = get()) }
    }
