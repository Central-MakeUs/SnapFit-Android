package memory.fabricators.snapfit.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography


@Composable
fun FilterButton(
    filter: Filter,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = LocalColorScheme.current.primaryBlack,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = filter.text,
            style = LocalTypography.current.body1Semibold.copy(
                color = LocalColorScheme.current.primaryWhite,
            ),
        )
    }
}

data class Filter(
    val id: String,
    val text: String,
)
