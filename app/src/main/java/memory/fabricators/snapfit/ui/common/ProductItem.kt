package memory.fabricators.snapfit.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@Composable
fun ProductItem(
    backgroundImageUrl: String,
    title: @Composable () -> Unit,
    tags: @Composable RowScope.() -> Unit,
    price: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    favoriteIcon: (@Composable () -> Unit)? = null,
) {
    val density = LocalDensity.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var imageBoxWidth by remember { mutableStateOf(0.dp) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    imageBoxWidth = with(density) {
                        it.size.width.toDp()
                    }
                },
        ) {
            AsyncImage(
                model = backgroundImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(imageBoxWidth)
                    .clip(RoundedCornerShape(5.dp)),
            )
            if (favoriteIcon != null) {
                Box(
                    modifier = Modifier.align(Alignment.TopEnd),
                ) {
                    CompositionLocalProvider(
                        value = LocalContentColor provides LocalColorScheme.current.primaryWhite,
                        content = favoriteIcon,
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ProvideTextStyle(
                value = LocalTypography.current.body2Regular.copy(
                    color = LocalColorScheme.current.secondary400,
                ),
                content = subtitle,
            )
            ProvideTextStyle(
                value = LocalTypography.current.body1Semibold.copy(
                    color = LocalColorScheme.current.secondary500,
                ),
                content = title,
            )
            // TODO 태그 칩

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                content = tags,
            )
        }
        ProvideTextStyle(
            value = LocalTypography.current.body1Semibold.copy(
                color = LocalColorScheme.current.secondary500,
            ),
            content = price,
        )
    }
}

@Composable
fun ProductItemTag(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = LocalColorScheme.current.secondary100,
                shape = RoundedCornerShape(2.dp),
            )
            .padding(
                horizontal = 8.dp,
                vertical = 2.dp,
            ),
        content = {
            ProvideTextStyle(
                value = LocalTypography.current.caption2Regular.copy(
                    color = LocalColorScheme.current.secondary500,
                ),
                content = content,
            )
        },
    )
}

@Preview
@Composable
private fun ProductItemPreview() {
    //   ProductItem()
}
