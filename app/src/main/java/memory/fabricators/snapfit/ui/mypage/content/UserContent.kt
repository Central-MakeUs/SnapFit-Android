package memory.fabricators.snapfit.ui.mypage.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.SectionHeader
import memory.fabricators.snapfit.ui.mypage.composables.Setting
import memory.fabricators.snapfit.ui.mypage.composables.SettingsList

@Composable
fun UserContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        HorizontalDivider(
            color = LocalColorScheme.current.secondary100,
        )
        // TODO
        SectionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            title = { Text(text = "메이커 관리") },
        )
        SettingsList(
            settings = listOf(
                Setting(
                    title = {
                        Text(text = "사진작가로 전환")
                    },
                    onClick = {

                    },
                ),
                Setting(
                    title = {
                        Text(text = "상품관리")
                    },
                    onClick = {

                    },
                ),
                Setting(
                    title = {
                        Text(text = "예약관리")
                    },
                    onClick = {

                    },
                ),
            ),
        )
    }
}
