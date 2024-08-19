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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.BasicDialog
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.data.user.model.Vibe

@Composable
fun PhotoMoodSelectionContent(
    onNext: () -> Unit,
    vibes: List<Vibe>?,
    modifier: Modifier = Modifier,
) {
    val formedMoods = remember { mutableStateListOf<Mood>() }

    val (shouldShowDialog, onChangeShouldShowDialog) = remember { mutableStateOf(false) }
    fun closeDialog() = onChangeShouldShowDialog(false)

    if (shouldShowDialog) {
        // TODO
        BasicDialog(
            content = {
                Text("최대 2개까지 선택이 가능합니다")
            },
            primaryAction = {
                TextButton(
                    onClick = { closeDialog() },
                ) {
                    Text(
                        text = "확인",
                        modifier = Modifier.padding(all = 12.dp),
                    )
                }
            },
            secondaryAction = {
                TextButton(
                    onClick = { closeDialog() },
                ) {
                    Text(
                        text = "취소",
                        modifier = Modifier.padding(all = 12.dp),
                    )
                }
            },
            onDismissRequest = { closeDialog() },
        )
    }

    LaunchedEffect(key1 = vibes) {
        if (vibes != null) {
            formedMoods.addAll(
                vibes.map {
                    Mood(
                        vibe = it,
                        selected = false,
                    )
                },
            )
        }
    }

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
                text = stringResource(id = R.string.signup_photoMoodSelection_title),
                style = LocalTypography.current.title1Semibold,
                color = LocalColorScheme.current.primaryBlack,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.signup_photoMoodSelection_subtitle),
                style = LocalTypography.current.body1Regular,
                color = LocalColorScheme.current.secondary400,
            )
            Spacer(modifier = Modifier.height(24.dp))
            MoodList(
                moods = formedMoods,
                onMoodClick = {
                    formedMoods.replaceAll { mood ->
                        if (mood.vibe.id == it.vibe.id) {
                            if (mood.selected) {
                                return@replaceAll mood.copy(
                                    selected = false,
                                )
                            } else {
                                if (formedMoods.count { it.selected } >= 2) {
                                    onChangeShouldShowDialog(true)
                                    mood
                                } else {
                                    mood.copy(
                                        selected = !mood.selected,
                                    )
                                }
                            }
                        } else {
                            mood
                        }
                    }
                },
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
            Text(
                text = stringResource(id = R.string.signup_photoMoodSelection_button_main),
            )
        }
    }
}

data class Mood(
    val vibe: Vibe,
    var selected: Boolean,
)

@Composable
private fun MoodList(
    moods: List<Mood>,
    onMoodClick: (Mood) -> Unit,
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
            key = { it.vibe.id },
        ) { mood ->
            MoodListItem(
                selected = mood.selected,
                onSelect = { onMoodClick(mood) },
            ) {
                Text(text = mood.vibe.name)
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
