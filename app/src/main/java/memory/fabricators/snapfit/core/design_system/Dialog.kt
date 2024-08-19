package memory.fabricators.snapfit.core.design_system

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
    ) {
        Text(text = "하이하이염")
    }
}
