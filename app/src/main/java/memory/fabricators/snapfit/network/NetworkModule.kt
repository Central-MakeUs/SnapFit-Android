package memory.fabricators.snapfit.network

import memory.fabricators.snapfit.network.login.di.loginNetworkModule
import memory.fabricators.snapfit.network.post.di.postNetworkModule
import memory.fabricators.snapfit.network.reservation.di.reservationNetworkModule
import memory.fabricators.snapfit.network.user.di.userNetworkModule
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module
    get() = module {
        includes(
            loginNetworkModule,
            postNetworkModule,
            reservationNetworkModule,
            userNetworkModule,
        )
    }
