package memory.fabricators.snapfit.ui.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.TextField

@Composable
fun NicknameContent(
    onNext: () -> Unit,
    nickname: String,
    onChangeNickname: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 32.dp,
                ),
                text = stringResource(id = R.string.signUp_nickname_pleaseEnterNickname),
                style = LocalTypography.current.title1Semibold,
                color = LocalColorScheme.current.primaryBlack,
            )
            Spacer(modifier = Modifier.height(48.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                    ),
                value = nickname,
                onValueChange = onChangeNickname,
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 24.dp,
                ),
            onClick = onNext,
        ) {
            Text(text = stringResource(id = R.string.signUp_nickname_button_next))
        }
    }
}
