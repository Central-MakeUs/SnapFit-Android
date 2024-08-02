package memory.fabricators.snapfit.ui.main.mypage.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@Composable
fun SettingsList(
    settings: List<Setting>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        settings.forEachIndexed { index, setting ->
            SettingsListItem(
                modifier = Modifier.fillMaxWidth(),
                setting = setting,
            )
            if (index != settings.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    color = LocalColorScheme.current.secondary100,
                )
            }
        }
    }
}

@Composable
fun SettingsListItem(
    setting: Setting,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = setting.onClick,
            )
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.body2Regular.copy(
                color = LocalColorScheme.current.primaryBlack,
            ),
            content = setting.title,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = LocalColorScheme.current.secondary300,
        )
    }
}

data class Setting(
    val title: @Composable () -> Unit,
    val onClick: () -> Unit,
)
