package memory.fabricators.snapfit.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@Composable
fun PhotoMoodSelectionContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 32.dp,
                ),
                text = stringResource(id = R.string.signUp_photoMoodSelection_title_whichMoodDoYouWant),
                style = LocalTypography.current.title1Semibold,
                color = LocalColorScheme.current.primaryBlack,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.signUp_photoMoodSelection_subtitle_canChooseTwoMoods),
                style = LocalTypography.current.body1Regular,
                color = LocalColorScheme.current.secondary400,
            )
            Spacer(modifier = Modifier.height(24.dp))
            MoodList(
                // TODO
                moods = listOf(
                    Mood(
                        id = "123",
                        title = "분위기",
                        selected = true,
                    ),
                    Mood(
                        id = "1234",
                        title = "분위기",
                        selected = false,
                    ),
                    Mood(
                        id = "12345",
                        title = "분위기",
                        selected = true,
                    ),
                    Mood(
                        id = "123456",
                        title = "분위기",
                        selected = false,
                    ),
                ),
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
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = stringResource(id = R.string.signUp_photoMoodSelection_button_start),
            )
        }
    }
}

data class Mood(
    val id: String,
    val title: String,
    val selected: Boolean,
)

@Composable
private fun MoodList(
    moods: List<Mood>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = moods,
            key = { it.id },
        ) { mood ->
            MoodListItem(
                selected = mood.selected,
                onSelect = {

                },
            ) {
                Text(text = mood.title)
            }
        }
    }
}

@Composable
private fun MoodListItem(
    selected: Boolean,
    onSelect: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    onSelect(!selected)
                },
            )
            .then(
                other = run {
                    val shape = RoundedCornerShape(2.dp)
                    if (selected) {
                        Modifier.background(
                            color = LocalColorScheme.current.primaryBlack,
                            shape = shape,
                        )
                    } else {
                        Modifier.border(
                            width = 1.dp,
                            color = LocalColorScheme.current.secondary300,
                            shape = shape,
                        )
                    }
                },
            )
            .padding(
                all = 16.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.body1Regular.copy(
                color = if (selected) {
                    LocalColorScheme.current.primaryWhite
                } else {
                    LocalColorScheme.current.secondary400
                },
            ),
            content = content,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PhotoMoodSelectionContentPreview() {
    PhotoMoodSelectionContent()
}
