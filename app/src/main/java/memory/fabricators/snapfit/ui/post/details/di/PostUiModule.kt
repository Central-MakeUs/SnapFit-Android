package memory.fabricators.snapfit.ui.post.details.di

import memory.fabricators.snapfit.ui.post.details.PostDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val postUiModule: Module
    get() = module {
        viewModel { PostDetailsViewModel(postRepository = get()) }
    }