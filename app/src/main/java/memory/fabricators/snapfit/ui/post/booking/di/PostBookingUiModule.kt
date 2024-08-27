package memory.fabricators.snapfit.ui.post.booking.di

import memory.fabricators.snapfit.ui.post.booking.BookingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val postBookingUiModule: Module
    get() = module {
        viewModel { BookingViewModel(postRepository = get()) }
    }
