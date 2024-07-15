package memory.fabricators.snapfit

import androidx.compose.runtime.Composable
import memory.fabricators.snapfit.ui.auth.AuthScreen
import org.koin.compose.KoinApplication

@Composable
fun FabricatorsApp() {
    KoinApplication(
        application = {
            modules()
        },
    ) {
        AuthScreen()
    }
}
