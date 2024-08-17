package memory.fabricators.snapfit.data.login.di

import memory.fabricators.snapfit.data.login.LoginRepository
import memory.fabricators.snapfit.data.login.LoginRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val loginDataModule: Module
    get() = module {
        single<LoginRepository> { LoginRepositoryImpl(loginNetworkDataSource = get()) }
    }
