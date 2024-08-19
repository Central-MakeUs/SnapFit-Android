package memory.fabricators.snapfit.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDialog(
    content: @Composable () -> Unit,
    primaryAction: @Composable () -> Unit,
    secondaryAction: (@Composable () -> Unit)? = null,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .width(350.dp)
            .height(200.dp)
            .background(
                color = LocalColorScheme.current.primaryWhite,
                shape = RoundedCornerShape(5.dp),
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            ProvideTextStyle(
                value = LocalTypography.current.body1Semibold.copy(
                    color = LocalColorScheme.current.primaryBlack,
                ),
                content = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        content()
                    }
                },
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 5.dp,
                            bottomEnd = 5.dp,
                        ),
                    ),
            ) {
                if (secondaryAction != null) {
                    Surface(
                        modifier = Modifier.weight(1f),
                        color = LocalColorScheme.current.secondary100,
                    ) {
                        ProvideTextStyle(
                            value = LocalTypography.current.caption1Semibold.copy(
                                color = LocalColorScheme.current.primaryBlack,
                            ),
                            content = secondaryAction,
                        )
                    }
                }
                Surface(
                    modifier = Modifier.weight(1f),
                    color = LocalColorScheme.current.primaryBlack,
                ) {
                    ProvideTextStyle(
                        value = LocalTypography.current.caption1Semibold.copy(
                            color = LocalColorScheme.current.primaryWhite,
                        ),
                        content = primaryAction,
                    )
                }
            }
        }
    }
}
