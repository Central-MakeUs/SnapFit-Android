package memory.fabricators.snapfit.ui.post.booking

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography
import memory.fabricators.snapfit.core.design_system.SectionHeader
import memory.fabricators.snapfit.core.design_system.TextField
import memory.fabricators.snapfit.ui.common.CircleChip
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    postId: Long,
    onNavigateUp: () -> Unit,
    onOpenReservationResult: (reservationId: Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()

    val focus = LocalFocusManager.current
    val context = LocalContext.current

    val (showDatePicker, onChangeShowDatePicker) = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val (showTimePicker, onChangeShowTimePicker) = remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()

    val (showMinutePicker, onChangeShowMinutePicker) = remember { mutableStateOf(false) }

    val (minutes, onChangeMinutes) = remember { mutableStateOf("") }
    val (preferLocation, onChangePreferLocation) = remember { mutableStateOf("") }
    val (preferDate, onChangePreferDate) = remember { mutableStateOf("") }
    val (preferTime, onChangePreferTime) = remember { mutableStateOf("") }
    val (countOfPeople, onChangeCountOfPeople) = remember { mutableIntStateOf(1) }
    val (email, onChangeEmail) = remember { mutableStateOf("") }
    val (phoneNumber, onChangePhoneNumber) = remember { mutableStateOf("") }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { onChangeShowDatePicker(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        val date = Date(datePickerState.selectedDateMillis!!)
                        val year = (date.year + 1900).toString().padStart(
                            length = 2,
                            padChar = '0',
                        )
                        val month = (date.month + 1).toString().padStart(
                            length = 2,
                            padChar = '0',
                        )
                        val day = date.date.toString().padStart(
                            length = 2,
                            padChar = '0',
                        )
                        onChangePreferDate("$year-$month-$day")
                        onChangeShowDatePicker(false)
                    },
                ) {
                    Text(text = "확인")
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
            )
        }
    }

    if (showTimePicker) {
        DatePickerDialog(
            onDismissRequest = { onChangeShowTimePicker(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        val hour = timePickerState.hour.toString().padStart(
                            length = 2,
                            padChar = '0',
                        )
                        val minute = "00"
                        onChangePreferTime("$hour:$minute")
                        onChangeShowTimePicker(false)
                    },
                ) {
                    Text(text = "확인")
                }
            },
        ) {
            TimePicker(
                state = timePickerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
            )
        }
    }

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            BookingSideEffect.CheckTimeFormat -> Toast.makeText(
                context,
                "시간 형식을 확인해주세요.",
                Toast.LENGTH_SHORT,
            ).show()

            is BookingSideEffect.ReservationCreated -> onOpenReservationResult(sideEffect.reservationId)
        }
    }

    LaunchedEffect(key1 = postId) {
        viewModel.fetchPostDetails(postId)
    }

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
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPaddings)
                .imePadding(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                SectionHeader(
                    title = { Text(text = "주문상품") },
                )
                AnimatedVisibility(
                    visible = state.postDetails != null,
                ) {
                    val postDetails = state.postDetails!!
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = postDetails.thumbnail,
                            contentDescription = postDetails.title,
                            modifier = Modifier
                                .size(
                                    size = 130.dp,
                                )
                                .clip(
                                    shape = RoundedCornerShape(5.dp),
                                ),
                            contentScale = ContentScale.Crop,
                        )
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = postDetails.title,
                                style = LocalTypography.current.body2Semibold,
                                color = LocalColorScheme.current.secondary500,
                            )
                            Text(
                                text = postDetails.locations.joinToString(),
                                style = LocalTypography.current.caption2Regular,
                                color = LocalColorScheme.current.secondary500,
                            )
                            Text(
                                text = postDetails.personPrice.toString(),
                                style = LocalTypography.current.caption1Semibold,
                                color = LocalColorScheme.current.secondary500,
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                postDetails.vibes.forEach { vibe ->
                                    MemoryRecommendationTag {
                                        Text(text = vibe)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            HorizontalDivider(
                thickness = 5.dp,
                color = LocalColorScheme.current.secondary100,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = { Text(text = "옵션") },
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        TextField(
                            value = minutes.run { if (isNotEmpty()) return@run this + "분" else this },
                            onValueChange = onChangeMinutes,
                            modifier = Modifier.fillMaxWidth(),
                            hintValue = "시간을 선택해주세요",
                            readOnly = true,
                        )
                        IconButton(
                            onClick = { onChangeShowMinutePicker(true) },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_arrow_bottom),
                                contentDescription = "open",
                                tint = LocalColorScheme.current.secondary300,
                            )
                            if (state.postDetails != null) DropdownMenu(
                                expanded = showMinutePicker,
                                onDismissRequest = { onChangeShowMinutePicker(false) },
                            ) {
                                state.postDetails!!.prices.forEach { price ->
                                    DropdownMenuItem(
                                        text = { Text(text = price.min.toString() + "분") },
                                        onClick = {
                                            onChangeMinutes(price.min.toString())
                                            onChangeShowMinutePicker(false)
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(text = "원하시는 위치를 적어주세요")
                                Text(
                                    text = "[필수]",
                                    style = LocalTypography.current.body2Regular,
                                    color = LocalColorScheme.current.secondary400,
                                )
                            }
                        },
                    )
                    TextField(
                        value = preferLocation,
                        onValueChange = onChangePreferLocation,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        hintValue = "메이커와 상담후 장소 변경이 가능해요",
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(text = "원하시는 날짜를 선택해주세요")
                                Text(
                                    text = "[필수]",
                                    style = LocalTypography.current.body2Regular,
                                    color = LocalColorScheme.current.secondary400,
                                )
                            }
                        },
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        TextField(
                            value = preferDate,
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            hintValue = "날짜를 선택해주세요",
                            readOnly = true,
                        )
                        IconButton(
                            onClick = { onChangeShowDatePicker(true) },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_arrow_bottom),
                                contentDescription = "open",
                                tint = LocalColorScheme.current.secondary300,
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(text = "원하시는 시간을 선택해주세요")
                                Text(
                                    text = "[필수]",
                                    style = LocalTypography.current.body2Regular,
                                    color = LocalColorScheme.current.secondary400,
                                )
                            }
                        },
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        TextField(
                            value = preferTime,
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            hintValue = "시간을 선택해주세요",
                            readOnly = true,
                        )
                        IconButton(
                            onClick = { onChangeShowTimePicker(true) },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_arrow_bottom),
                                contentDescription = "open",
                                tint = LocalColorScheme.current.secondary300,
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = {
                            Text(text = "인원을 선택해주세요")
                        },
                    )
                    Text(
                        text = "- 2인 이상 1인당 ${state.postDetails?.personPrice ?: "-"}원 추가",
                        style = LocalTypography.current.body1Regular,
                        color = LocalColorScheme.current.secondary300,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                end = 16.dp,
                            ),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 24.dp,
                            alignment = Alignment.End,
                        ),
                    ) {
                        Text(
                            text = "성인",
                            style = LocalTypography.current.body1Regular,
                            color = LocalColorScheme.current.primaryBlack,
                        )
                        CircleChip(
                            onClick = {
                                if (countOfPeople > 1) {
                                    onChangeCountOfPeople(countOfPeople - 1)
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                        }
                        Text(
                            text = countOfPeople.toString(),
                            style = LocalTypography.current.body1Regular,
                            color = LocalColorScheme.current.primaryBlack,
                        )
                        CircleChip(
                            onClick = {
                                if (countOfPeople < 10) {
                                    onChangeCountOfPeople(countOfPeople + 1)
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(text = "연락받으실 이메일을 적어주세요")
                                Text(
                                    text = "[필수]",
                                    style = LocalTypography.current.body2Regular,
                                    color = LocalColorScheme.current.secondary400,
                                )
                            }
                        },
                    )
                    TextField(
                        value = email,
                        onValueChange = onChangeEmail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        hintValue = "abc@def.com",
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    SectionHeader(
                        title = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(text = "연락받으실 전화번호를 적어주세요")
                                Text(
                                    text = "[필수]",
                                    style = LocalTypography.current.body2Regular,
                                    color = LocalColorScheme.current.secondary400,
                                )
                            }
                        },
                    )
                    TextField(
                        value = phoneNumber,
                        onValueChange = onChangePhoneNumber,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        hintValue = "01012345678",
                    )
                }
            }
            Button(
                onClick = {
                    try {
                        viewModel.createReservation(
                            email = email,
                            phoneNumber = phoneNumber,
                            postId = postId,
                            makerId = state.postDetails!!.maker.id,
                            minutes = minutes.toLong(),
                            price = state.postDetails!!.prices.find { it.min == minutes.toLong() }!!.price,
                            person = countOfPeople.toLong(),
                            personPrice = state.postDetails!!.personPrice,
                            reservationLocation = preferLocation,
                            reservationTime = run {
                                val date = Date(datePickerState.selectedDateMillis!!).apply {
                                    hours = timePickerState.hour
                                    setMinutes(timePickerState.minute)
                                }
                                LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                                    .toString()
                            },
                        )
                    } catch (_: Exception) {
                        Toast.makeText(
                            context,
                            "입력한 값을 확인해주세요",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                    ),
                enabled = minutes.isNotBlank()
                        && preferLocation.isNotBlank()
                        && preferDate.isNotBlank()
                        && preferTime.isNotBlank()
                        && countOfPeople != 0
                        && email.isNotBlank()
                        && phoneNumber.isNotBlank(),
            ) {
                Text(text = "예약하기")
            }
        }
    }
}

@Composable
private fun MemoryRecommendationTag(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = LocalColorScheme.current.accentPink.copy(
                    alpha = 0.1f,
                ),
                shape = RoundedCornerShape(2.dp),
            )
            .padding(
                horizontal = 8.dp,
                vertical = 2.dp,
            ),
    ) {
        ProvideTextStyle(
            value = LocalTypography.current.caption1Semibold.copy(
                color = LocalColorScheme.current.accentPink,
            ),
            content = content,
        )
    }
}
