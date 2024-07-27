package memory.fabricators.snapfit.core.design_system

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionHeader(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HorizontalDivider(
            modifier = Modifier
                .width(16.dp)
                .align(Alignment.Bottom),
            color = LocalColorScheme.current.primaryBlack,
            thickness = 2.dp,
        )
        Surface(
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            CompositionLocalProvider(
                value = LocalTextStyle provides LocalTypography.current.body1Semibold.copy(
                    color = LocalColorScheme.current.primaryBlack,
                ),
                content = title,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (action != null) {
            CompositionLocalProvider(
                value = LocalContentColor provides LocalColorScheme.current.primaryBlack,
                content = action,
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
)
private fun SectionTitlePreview(
    modifier: Modifier = Modifier,
) {
    SectionHeader(
        title = {
            Text(text = "이런 사진 어때요?")
        },
        action = {
            IconButton(
                modifier = Modifier.size(16.dp),
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                )
            }
        }
    )
}
