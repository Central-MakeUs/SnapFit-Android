package memory.fabricators.snapfit.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
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

            MainSections.PHOTO_LIST -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Red),
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
    PHOTO_LIST(
        route = "photo_list",
        labelRes = R.string.main_bottomNavigation_photoListRoute,
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
