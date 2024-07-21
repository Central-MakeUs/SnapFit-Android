package memory.fabricators.snapfit

import androidx.compose.runtime.Composable
import memory.fabricators.snapfit.ui.signup.TermsContent
import org.koin.compose.KoinApplication

@Composable
fun SnapfitApp() {
    KoinApplication(
        application = {
            modules()
        },
    ) {
        TermsContent()
    }
}
