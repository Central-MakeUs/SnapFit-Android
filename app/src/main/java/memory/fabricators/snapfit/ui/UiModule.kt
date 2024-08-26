package memory.fabricators.snapfit.ui

import memory.fabricators.snapfit.ui.main.artistlist.di.mainArtistUiModule
import memory.fabricators.snapfit.ui.main.home.di.mainHomeUiModule
import memory.fabricators.snapfit.ui.post.details.di.postUiModule
import memory.fabricators.snapfit.ui.signup.di.signUpUiModule
import memory.fabricators.snapfit.ui.start.di.startUiModule
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModule: Module
    get() = module {
        includes(
            signUpUiModule,
            startUiModule,
            mainArtistUiModule,
            mainHomeUiModule,
            postUiModule,
        )
    }
