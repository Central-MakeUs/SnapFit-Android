package memory.fabricators.snapfit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import memory.fabricators.snapfit.data.dataModule
import memory.fabricators.snapfit.database.databaseModule
import memory.fabricators.snapfit.datastore.dataStoreModule
import memory.fabricators.snapfit.network.networkModule
import memory.fabricators.snapfit.ui.main.MainScreen
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
        val navController = rememberNavController()
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = SnapfitDestinations.MAIN.route,
        ) {
            composable(route = SnapfitDestinations.MAIN.route) {
                MainScreen()
            }
            composable(route = SnapfitDestinations.START.route) {

            }
            composable(route = SnapfitDestinations.SIGN_UP.route) {

            }
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

enum class SnapfitDestinations(
    val route: String,
) {
    START(
        route = "start",
    ),
    SIGN_UP(
        route = "sign_up",
    ),
    MAIN(
        route = "main",
    ),
}
