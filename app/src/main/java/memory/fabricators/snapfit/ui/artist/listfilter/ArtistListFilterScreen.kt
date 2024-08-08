package memory.fabricators.snapfit.ui.artist.listfilter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistListFilterScreen(
    modifier: Modifier = Modifier,
) {
    val dummyMoodFilters = listOf(
        Filter(
            id = "1",
            text = "러블리",
        ),
        Filter(
            id = "12",
            text = "시크",
        ),
        Filter(
            id = "123",
            text = "키치",
        ),
        Filter(
            id = "1234",
            text = "차분함",
        ),
    )

    val dummyPriceFilters = listOf(
        Filter(
            id = "1",
            text = "1~2만원",
        ),
        Filter(
            id = "12",
            text = "3~4만원",
        ),
        Filter(
            id = "123",
            text = "4~5만원",
        ),
        Filter(
            id = "1234",
            text = "5~6만원",
        ),
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "필터") },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            FilterList(
                title = {
                    Text(
                        text = "분위기",
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 16.dp,
                        ),
                    )
                },
                filters = dummyMoodFilters,
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                color = LocalColorScheme.current.secondary100,
            )
            FilterList(
                title = {
                    Text(
                        text = "가격",
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 16.dp,
                        ),
                    )
                },
                filters = dummyMoodFilters,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterList(
    title: @Composable () -> Unit,
    filters: List<Filter>,
    modifier: Modifier = Modifier,
    filterContentPaddings: PaddingValues = PaddingValues(all = 16.dp),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.body1Semibold.copy(
                color = LocalColorScheme.current.primaryBlack,
            ),
            content = title,
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(filterContentPaddings),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            filters.forEach { filter ->
                FilterButton(
                    filter = filter,
                    modifier = Modifier.weight(
                        weight = 1f,
                    ),
                )
            }
        }
    }
}

@Composable
private fun FilterButton(
    filter: Filter,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = LocalColorScheme.current.primaryBlack,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = filter.text,
            style = LocalTypography.current.body1Semibold.copy(
                color = LocalColorScheme.current.primaryWhite,
            ),
        )
    }
}

data class Filter(
    val id: String,
    val text: String,
)
