package memory.fabricators.snapfit

import androidx.compose.runtime.Composable
import memory.fabricators.snapfit.data.dataModule
import memory.fabricators.snapfit.database.databaseModule
import memory.fabricators.snapfit.datastore.dataStoreModule
import memory.fabricators.snapfit.network.networkModule
import memory.fabricators.snapfit.ui.signup.NicknameContent
import memory.fabricators.snapfit.ui.signup.TermsContent
import memory.fabricators.snapfit.ui.uiModule
import org.koin.compose.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.module

@Composable
fun SnapfitApp() {
    KoinApplication(
        application = {
            modules(snapfitModule)
        },
    ) {
        NicknameContent()
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
