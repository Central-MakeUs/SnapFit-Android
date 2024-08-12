package memory.fabricators.snapfit

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import memory.fabricators.snapfit.data.dataModule
import memory.fabricators.snapfit.database.databaseModule
import memory.fabricators.snapfit.datastore.dataStoreModule
import memory.fabricators.snapfit.network.networkModule
import memory.fabricators.snapfit.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class SnapfitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)

        startKoin {
            androidLogger()
            androidContext(this@SnapfitApplication)
            modules(
                snapfitModule,
            )
        }
    }
}

val snapfitModule: Module
    get() = module {
        includes(
            dataModule,
            databaseModule,
            dataStoreModule,
            networkModule,
            uiModule,
        )
    }
