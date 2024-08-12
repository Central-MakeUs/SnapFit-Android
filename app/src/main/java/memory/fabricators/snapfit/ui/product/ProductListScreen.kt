package memory.fabricators.snapfit.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.SectionHeader
import memory.fabricators.snapfit.ui.common.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_arrow_left),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { innerPaddings ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
                .verticalScroll(rememberScrollState()),
        ) {
            SectionHeader(
                title = { Text(text = "등록된 상품") },
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ProductItem(
                    backgroundImageUrl = "",
                    title = { Text(text = "제목") },
                    tags = listOf(),
                    price = { Text(text = "가격") },
                    subtitle = { Text(text = "부제목") },
                    modifier = Modifier.weight(1f),
                )

                ProductItem(
                    backgroundImageUrl = "",
                    title = { Text(text = "제목") },
                    tags = listOf(),
                    price = { Text(text = "가격") },
                    subtitle = { Text(text = "부제목") },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductListScreenPreview() {
    ProductListScreen()
}
