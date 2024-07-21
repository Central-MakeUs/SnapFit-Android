package memory.fabricators.snapfit.core.design_system

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class Typography(
    val title1Semibold: TextStyle,
    val title2Semibold: TextStyle,
    val title2Regular: TextStyle,
    val subtitle1Semibold: TextStyle,
    val body1Semibold: TextStyle,
    val body1Regular: TextStyle,
    val body2Semibold: TextStyle,
    val body2Regular: TextStyle,
    val caption1Semibold: TextStyle,
    val caption2Regular: TextStyle,
)

fun snapfitTypography(
    title1Semibold: TextStyle = TextStyle(
        fontSize = 23.sp,
        lineHeight = 31.sp,
        fontWeight = FontWeight.SemiBold
    ),
    title2Semibold: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.SemiBold
    ),
    title2Regular: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 27.sp,
        fontWeight = FontWeight.Medium
    ),
    subtitle1Semibold: TextStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body1Semibold: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body1Regular: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    body2Semibold: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body2Regular: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    caption1Semibold: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    caption2Regular: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    )
): Typography = Typography(
    title1Semibold = title1Semibold,
    title2Semibold = title2Semibold,
    title2Regular = title2Regular,
    subtitle1Semibold = subtitle1Semibold,
    body1Semibold = body1Semibold,
    body1Regular = body1Regular,
    body2Semibold = body2Semibold,
    body2Regular = body2Regular,
    caption1Semibold = caption1Semibold,
    caption2Regular = caption2Regular
)

val LocalTypography = staticCompositionLocalOf { snapfitTypography() }
