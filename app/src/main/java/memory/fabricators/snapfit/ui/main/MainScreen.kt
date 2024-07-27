package memory.fabricators.snapfit.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = LocalColorScheme.current.primaryWhite,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LocalColorScheme.current.primaryWhite,
                ),
                title = {
                    Icon(
                        tint = LocalColorScheme.current.secondary500,
                        painter = painterResource(id = R.drawable.app_logo_dark),
                        contentDescription = null,
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(id = R.string.main_notification),
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            // TODO
            Header(
                username = "한소희",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 32.dp,
                    ),
            )
            SectionHeader(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.main_sectionTitle_photoSuggestions),
                    )
                },
                action = {
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = stringResource(
                                id = R.string.main_sectionTitle_photoSuggestions,
                            ),
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun Header(
    username: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(
                    id = R.string.main_title_helloUser,
                    username,
                ),
                style = LocalTypography.current.title1Semibold.copy(
                    color = LocalColorScheme.current.primaryBlack,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.main_subtitle_youCanFindPhotoOfMoods,
                ),
                style = LocalTypography.current.body2Regular.copy(
                    color = LocalColorScheme.current.secondary400,
                ),
            )
        }
        // Image(painter = , contentDescription = )
        Box(
            modifier = Modifier
                .size(
                    50.dp,
                )
                .background(
                    color = Color.Red,
                    shape = CircleShape,
                ),
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
