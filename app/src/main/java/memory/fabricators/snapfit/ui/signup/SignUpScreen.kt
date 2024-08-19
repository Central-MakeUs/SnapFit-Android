package memory.fabricators.snapfit.ui.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SignUpScreen(
    onNavigateUp: () -> Unit,
    onOpenMain: () -> Unit,
    socialAccessToken: String,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { SignUpContents.entries.size }
    val (nickname, onChangeNickname) = remember { mutableStateOf("") }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            SignUpSideEffect.SignUpFailure -> {}
            SignUpSideEffect.SignUpSuccess -> onOpenMain()
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LocalColorScheme.current.primaryWhite,
                    actionIconContentColor = LocalColorScheme.current.primaryBlack,
                ),
                title = { /* explicit blank */ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (pagerState.currentPage > 0) {
                                with(pagerState) {
                                    scope.launch {
                                        animateScrollToPage(currentPage - 1)
                                    }
                                }
                            } else {
                                onNavigateUp()
                            }
                        },
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_arrow_left),
                            contentDescription = stringResource(id = R.string.navigate_back),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            beyondBoundsPageCount = 2,
        ) { pageIndex ->
            when (pageIndex) {
                0 -> TermsContent(
                    onNext = { scope.launch { pagerState.animateScrollToPage(1) } },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )

                1 -> NicknameContent(
                    onNext = { scope.launch { pagerState.animateScrollToPage(2) } },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    nickname = nickname,
                    onChangeNickname = onChangeNickname,
                )

                2 -> PhotoMoodSelectionContent(
                    onNext = { selectedVibes ->
                        viewModel.signUp(
                            selectedVibes = selectedVibes,
                            socialAccessToken = socialAccessToken,
                            nickname = nickname,
                        )
                    },
                    vibes = state.vibes,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
        }
    }
    BackHandler(
        enabled = pagerState.currentPage > 0,
    ) {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
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
