package memory.fabricators.snapfit.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import memory.fabricators.snapfit.R
import memory.fabricators.snapfit.core.design_system.Button
import memory.fabricators.snapfit.core.design_system.LocalColorScheme
import memory.fabricators.snapfit.core.design_system.LocalTypography

@Composable
fun TermsContent(
    onNext: () -> Unit,
    requiredTermsAgreed: Boolean,
    onChangeRequiredTermsAgreed: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    val termsItems = remember {
        mutableStateListOf(
            TermsCheckItem(
                id = "1",
                isChecked = false,
                required = true,
                title = "[필수] 이용 약관",
                url = "https://mixolydian-beef-6a0.notion.site/04cb97bab76c40d68aa17475c6e53172?pvs=4",
            ),
            TermsCheckItem(
                id = "2",
                isChecked = false,
                required = true,
                title = "[필수] 개인정보처리방침",
                url = "https://mixolydian-beef-6a0.notion.site/497ab7ab659743c8b797e2c62e4c7bc9?pvs=4",
            ),
            TermsCheckItem(
                id = "3",
                isChecked = false,
                required = true,
                title = "[필수] 만 14세 이상입니다",
                url = "https://mixolydian-beef-6a0.notion.site/14-c96d3cf1df7c449690452b07c55459c9?pvs=4",
            ),
            TermsCheckItem(
                id = "4",
                isChecked = false,
                required = false,
                title = "[선택] 광고성 정보 수신 및 마케팅 활용 동의",
                url = "https://mixolydian-beef-6a0.notion.site/9bdc6cfbb2474b58ad4f99421feab6cf?pvs=4",
            ),
        )
    }

    val allChecked = termsItems.all { it.isChecked }
    val allRequiredChecked = termsItems.filter { it.required }.all { it.isChecked }
    fun onCheckAll() {
        termsItems.replaceAll {
            it.copy(isChecked = !allChecked)
        }
    }

    fun onItemCheck(id: String) {
        termsItems.replaceAll { item ->
            if (item.id == id) {
                item.copy(
                    isChecked = !item.isChecked,
                )
            } else {
                item
            }
        }
    }

    LaunchedEffect(key1 = allRequiredChecked) {
        onChangeRequiredTermsAgreed(allRequiredChecked)
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
                text = stringResource(id = R.string.signup_terms_title),
                style = LocalTypography.current.title1Semibold,
                color = LocalColorScheme.current.primaryBlack,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                ),
                text = stringResource(id = R.string.signup_terms_subtitle),
                style = LocalTypography.current.body1Regular,
                color = LocalColorScheme.current.secondary400,
            )
            Spacer(modifier = Modifier.height(32.dp))


            TermsCheckGroup(
                title = {
                    Text(
                        text = stringResource(id = R.string.signup_terms_button_agreeAll),
                    )
                },
                isGroupChecked = allChecked,
                onCheckAll = { onCheckAll() },
                onSingleItemCheck = { onItemCheck(it) },
                onOpenTermContent = { uriHandler.openUri(it) },
                terms = termsItems,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }
        Button(
            onClick = onNext,
            enabled = requiredTermsAgreed,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 24.dp,
                ),
        ) {
            Text(text = stringResource(id = R.string.signup_terms_button_main))
        }
    }
}

@Composable
private fun TermsCheckGroup(
    title: @Composable () -> Unit,
    isGroupChecked: Boolean,
    onCheckAll: () -> Unit,
    onSingleItemCheck: (id: String) -> Unit,
    onOpenTermContent: (url: String) -> Unit,
    terms: List<TermsCheckItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            CompositionLocalProvider(
                value = LocalContentColor provides if (isGroupChecked) {
                    LocalColorScheme.current.primaryWhite
                } else {
                    LocalColorScheme.current.secondary500
                },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .background(
                            color = if (isGroupChecked) {
                                LocalColorScheme.current.primaryBlack
                            } else {
                                LocalColorScheme.current.secondary200
                            },
                            shape = RoundedCornerShape(4.dp),
                        )
                        .clickable(
                            onClick = onCheckAll,
                        )
                        .padding(
                            horizontal = 20.dp,
                            vertical = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_check),
                        contentDescription = stringResource(id = R.string.signUp_terms_cd_agreeAll),
                        modifier = Modifier.size(24.dp),
                        tint = if (isGroupChecked) {
                            LocalColorScheme.current.primaryWhite
                        } else {
                            LocalColorScheme.current.secondary400
                        },
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ProvideTextStyle(
                        value = LocalTypography.current.body2Semibold.copy(
                            color = if (isGroupChecked) {
                                LocalColorScheme.current.primaryWhite
                            } else {
                                LocalColorScheme.current.secondary500
                            },
                        ),
                        content = title,
                    )
                }
            }
        }
        items(terms) { check ->
            TermsCheckItem(
                title = { Text(text = check.title) },
                isChecked = check.isChecked,
                onCheck = { onSingleItemCheck(check.id) },
                onClick = { onOpenTermContent(check.url) },
            )
        }
    }
}

@Composable
private fun TermsCheckItem(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    isChecked: Boolean,
    onCheck: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(all = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onCheck,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_check),
                tint = if (isChecked) LocalColorScheme.current.secondary500 else LocalColorScheme.current.secondary300,
                contentDescription = stringResource(id = R.string.signup_terms_cd_agree),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        ProvideTextStyle(
            value = LocalTypography.current.body2Regular.copy(
                color = LocalColorScheme.current.secondary500,
            ),
            content = title,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                tint = LocalColorScheme.current.secondary300,
                contentDescription = stringResource(id = R.string.signup_terms_cd_showDetails),
            )
        }
    }
}

data class TermsCheckItem(
    val id: String,
    var isChecked: Boolean,
    val required: Boolean,
    val title: String,
    val url: String,
)
