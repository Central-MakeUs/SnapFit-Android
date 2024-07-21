package memory.fabricators.snapfit.core.design_system

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

class ColorScheme(
    val primaryBlack: Color,
    val primaryWhite: Color,
    val accentYellow: Color,
    val accentPink: Color,
    val secondary500: Color,
    val secondary400: Color,
    val secondary300: Color,
    val secondary200: Color,
    val secondary100: Color,
)

fun snapfitLightColorScheme(
    primaryBlack: Color = Color(0xFF000000),
    primaryWhite: Color = Color(0xFFFFFFFF),
    accentYellow: Color = Color(0xFFFFCE21),
    accentPink: Color = Color(0xFFEC2F59),
    secondary500: Color = Color(0xFF171A1F),
    secondary400: Color = Color(0xFF9095A0),
    secondary300: Color = Color(0xFFBDBDBD),
    secondary200: Color = Color(0xFFD9D9D9),
    secondary100: Color = Color(0xFFF3F3F5),
): ColorScheme = ColorScheme(
    primaryBlack = primaryBlack,
    primaryWhite = primaryWhite,
    accentYellow = accentYellow,
    accentPink = accentPink,
    secondary500 = secondary500,
    secondary400 = secondary400,
    secondary300 = secondary300,
    secondary200 = secondary200,
    secondary100 = secondary100,
)

// TODO: fun snapfitDarkColorScheme(): ColorScheme

val LocalColorScheme = compositionLocalOf { snapfitLightColorScheme() }
