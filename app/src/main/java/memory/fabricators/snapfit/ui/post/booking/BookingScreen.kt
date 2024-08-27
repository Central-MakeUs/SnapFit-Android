package memory.fabricators.snapfit.ui.post.booking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.SectionHeader
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    postId: Long,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val (time, onChangeTime) = remember { mutableStateOf("") }
    val (preferLocation, onChangePreferLocation) = remember { mutableStateOf("") }
    val (preferTime, onChangePreferTime) = remember { mutableStateOf("") }
    val (countOfPeople, onChangeCountOfPeople) = remember { mutableIntStateOf(0) }
    val (email, onChangeEmail) = remember { mutableStateOf("") }
    val (phoneNumber, onChangePhoneNumber) = remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "예약") },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp,
                    ) {
                        Icon(
                            tint = LocalColorScheme.current.primaryBlack,
                            painter = painterResource(id = R.drawable.icon_arrow_left),
                            contentDescription = "back",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
            ) {
                SectionHeader(
                    title = { Text(text = "주문상품") },
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // AsyncImage(model = , contentDescription = )
                    /*AsyncImage(
                        model =,
                        contentDescription =,
                    )*/
                }
            }
        }
    }
}
