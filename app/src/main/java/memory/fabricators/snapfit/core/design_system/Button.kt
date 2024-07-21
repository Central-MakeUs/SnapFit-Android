package memory.fabricators.snapfit.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    innerPaddings: PaddingValues = PaddingValues(
        horizontal = 12.dp,
        vertical = 16.dp,
    ),
    shape: Shape = RoundedCornerShape(5.dp),
    content: @Composable () -> Unit,
) = Box(
    modifier = modifier
        .clickable(
            enabled = enabled,
            onClick = onClick,
        )
        .background(
            color = if (enabled) {
                LocalColorScheme.current.primaryBlack
            } else {
                LocalColorScheme.current.secondary100
            },
            shape = shape,
        )
        .padding(
            paddingValues = innerPaddings,
        ),
    contentAlignment = Alignment.Center,
) {
    ProvideTextStyle(
        value = LocalTypography.current.body1Semibold.copy(
            color = if (enabled) {
                LocalColorScheme.current.primaryWhite
            } else {
                LocalColorScheme.current.secondary300
            },
        ),
        content = content,
    )
}
