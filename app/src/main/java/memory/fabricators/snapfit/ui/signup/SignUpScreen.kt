package memory.fabricators.snapfit.ui.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onOpenMain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (content, onChangeContent) = remember { mutableStateOf(SignUpContents.TERMS) }
    val (nickname, onChangeNickname) = remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LocalColorScheme.current.primaryWhite,
                    actionIconContentColor = LocalColorScheme.current.primaryBlack,
                ),
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = stringResource(id = R.string.navigate_back),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        when (content) {
            SignUpContents.TERMS -> TermsContent(
                onNext = { onChangeContent(SignUpContents.NICKNAME) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            SignUpContents.NICKNAME -> NicknameContent(
                onNext = { onChangeContent(SignUpContents.PHOTO_MOOD_SELECTION) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                nickname = nickname,
                onChangeNickname = onChangeNickname,
            )

            SignUpContents.PHOTO_MOOD_SELECTION -> PhotoMoodSelectionContent(
                onNext = onOpenMain,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }
    }
}

enum class SignUpContents(
    val route: String,
) {
    TERMS(
        route = "terms",
    ),
    NICKNAME(
        route = "nickname",
    ),
    PHOTO_MOOD_SELECTION(
        route = "photo_mood_selection",
    ),
}
