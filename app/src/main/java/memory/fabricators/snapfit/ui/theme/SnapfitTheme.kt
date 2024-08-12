package memory.fabricators.snapfit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import memory.fabricators.snapfit.core.design_system.ColorScheme
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.Typography
import memory.fabricators.snapfit.core.design_system.snapfitLightColorScheme
import memory.fabricators.snapfit.core.design_system.snapfitTypography

@Composable
fun SnapfitTheme(
    typography: Typography = snapfitTypography(),
    colorScheme: ColorScheme = snapfitLightColorScheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColorScheme provides colorScheme,
    ) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                background = LocalColorScheme.current.primaryWhite,
                onBackground = LocalColorScheme.current.primaryBlack,
                surface = LocalColorScheme.current.primaryWhite,
                onSurface = LocalColorScheme.current.primaryBlack,
            ),
            content = content,
        )
    }
}
