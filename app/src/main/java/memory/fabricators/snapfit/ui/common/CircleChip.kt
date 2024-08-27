package memory.fabricators.snapfit.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme

@Composable
fun CircleChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = LocalColorScheme.current.primaryWhite,
    contentColor: Color = LocalColorScheme.current.secondary400,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier
            .semantics {
                role = Role.Button
            }
            .size(24.dp)
            .clickable(
                onClick = onClick,
            )
            .clip(
                shape = CircleShape,
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = contentColor,
                ),
                shape = CircleShape,
            ),
        color = containerColor,
    ) {
        CompositionLocalProvider(
            value = LocalContentColor provides contentColor,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                content = content,
            )
        }
    }
}

@Preview
@Composable
private fun CircleChipPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircleChip(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
            )
        }
    }
}
