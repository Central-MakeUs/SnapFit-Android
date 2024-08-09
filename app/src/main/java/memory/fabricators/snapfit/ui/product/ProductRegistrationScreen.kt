package memory.fabricators.snapfit.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.TextField
import memory.fabricators.snapfit.ui.common.Filter
import memory.fabricators.snapfit.ui.common.FilterButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductRegistrationScreen(
    modifier: Modifier = Modifier,
) {
    val (title, onTitleChange) = remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "상품등록") },
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
                .padding(innerPaddings)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "제목",
                    style = LocalTypography.current.body1Semibold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    hintValue = "제목을 작성해주세요",
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "글자수글자수",
                    modifier = Modifier.align(Alignment.End),
                    style = LocalTypography.current.body2Regular.copy(
                        color = LocalColorScheme.current.secondary400,
                    ),
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .background(
                            color = Color.Red,
                            shape = RoundedCornerShape(5.dp),
                        ),
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "제목",
                    style = LocalTypography.current.body1Semibold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    hintValue = "내용을 입력해주세요\n(최소 10자 - 최대 500자)",
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "글자수글자수",
                    modifier = Modifier.align(Alignment.End),
                    style = LocalTypography.current.body2Regular.copy(
                        color = LocalColorScheme.current.secondary400,
                    ),
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "스튜디오 유무",
                    style = LocalTypography.current.body1Semibold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    FilterButton(
                        filter = Filter(
                            id = "123",
                            text = "테스트",
                        ),
                    )
                    FilterButton(
                        filter = Filter(
                            id = "123",
                            text = "테스트",
                        ),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "분위기",
                    style = LocalTypography.current.body1Semibold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    FilterButton(
                        filter = Filter(
                            id = "123",
                            text = "분위기",
                        ),
                    )
                    FilterButton(
                        filter = Filter(
                            id = "123",
                            text = "분아래기",
                        ),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "옵션",
                    style = LocalTypography.current.body1Semibold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = "시간을 선택해주세요",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = "가격",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Text(text = "옵션 추가하기")
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "인원 추가 비용",
                    style = LocalTypography.current.body1Semibold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = "가격",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
        ) {
            Text(text = "등록하기")
        }
    }
}

@Preview
@Composable
private fun ProductRegistrationScreenPreview() {
    ProductRegistrationScreen()
}
