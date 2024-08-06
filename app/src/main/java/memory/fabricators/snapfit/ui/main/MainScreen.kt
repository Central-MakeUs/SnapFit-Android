package memory.fabricators.snapfit.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.ui.main.artistlist.ArtistListContent
import memory.fabricators.snapfit.ui.main.home.HomeContent
import memory.fabricators.snapfit.ui.main.mypage.MyPageContent

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val (currentSection, onCurrentSectionChange) = remember {
        mutableStateOf(MainSections.HOME)
    }
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        bottomBar = {
            BottomAppBar {
                MainSections.entries.forEach { section ->
                    val selected = section == currentSection
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onCurrentSectionChange(section) },
                        icon = {
                            Icon(
                                painter = painterResource(id = section.iconRes),
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = section.labelRes),
                            )
                        },
                    )
                }
            }
        }
    ) { innerPaddings ->
        when (currentSection) {
            MainSections.HOME -> HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPaddings.calculateBottomPadding(),
                    ),
            )

            MainSections.ARTIST_LIST -> ArtistListContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPaddings.calculateBottomPadding(),
                    ),
            )

            MainSections.MY_PAGE -> MyPageContent(
                isArtist = false,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = innerPaddings.calculateBottomPadding(),
                    ),
            )
        }
    }
}

enum class MainSections(
    val route: String,
    val labelRes: Int,
    val iconRes: Int,
) {
    HOME(
        route = "home",
        labelRes = R.string.main_bottomNavigation_homeRoute,
        // TODO
        iconRes = R.drawable.ic_check,
    ),
    ARTIST_LIST(
        route = "artist_list",
        labelRes = R.string.main_bottomNavigation_artistListRoute,
        // TODO
        iconRes = R.drawable.ic_check,
    ),
    MY_PAGE(
        route = "my_page",
        labelRes = R.string.main_bottomNavigation_myPage,
        // TODO
        iconRes = R.drawable.ic_check,
    ),
}
