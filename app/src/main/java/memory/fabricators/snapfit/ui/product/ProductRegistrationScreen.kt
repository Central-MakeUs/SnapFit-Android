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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.TextField

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
                .padding(innerPaddings),
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
        }
    }
}

@Preview
@Composable
private fun ProductRegistrationScreenPreview() {
    ProductRegistrationScreen()
}
