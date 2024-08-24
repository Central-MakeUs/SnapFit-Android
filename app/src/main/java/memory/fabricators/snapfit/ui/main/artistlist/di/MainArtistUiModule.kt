package memory.fabricators.snapfit.ui.main.artistlist.di

import memory.fabricators.snapfit.ui.main.artistlist.ArtistListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val mainArtistUiModule: Module
    get() = module {
        viewModel { ArtistListViewModel(postRepository = get()) }
    }
