package memory.fabricators.snapfit.data.post.di

import memory.fabricators.snapfit.data.post.PostRepository
import memory.fabricators.snapfit.data.post.PostRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val postDataModule: Module
    get() = module {
        single<PostRepository> { PostRepositoryImpl(postNetworkDataSource = get()) }
    }
