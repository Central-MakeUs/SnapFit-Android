package memory.fabricators.snapfit.data

import memory.fabricators.snapfit.data.login.di.loginDataModule
import memory.fabricators.snapfit.data.post.di.postDataModule
import memory.fabricators.snapfit.data.reservation.di.reservationDataModule
import memory.fabricators.snapfit.data.user.di.userDataModule
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule: Module
    get() = module {
        includes(
            loginDataModule,
            postDataModule,
            reservationDataModule,
            userDataModule,
        )
    }
