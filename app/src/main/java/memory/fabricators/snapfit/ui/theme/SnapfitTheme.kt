package memory.fabricators.snapfit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.snapfitLightColorScheme
import memory.fabricators.snapfit.core.design_system.snapfitTypography

@Composable
fun SnapfitTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalTypography provides snapfitTypography(),
        LocalColorScheme provides  snapfitLightColorScheme(),
    ) {
        MaterialTheme(
            content = content,
        )
    }
}
