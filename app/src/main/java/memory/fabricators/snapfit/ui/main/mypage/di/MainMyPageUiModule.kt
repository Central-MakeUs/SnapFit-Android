package memory.fabricators.snapfit.ui.main.mypage.di

import memory.fabricators.snapfit.ui.main.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainMyPageUiModule: Module
    get() = module {
        viewModel { MyPageViewModel(userRepository = get()) }
    }