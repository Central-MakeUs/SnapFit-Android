package memory.fabricators.snapfit.ui.signup

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier,
) {
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
                text = stringResource(id = R.string.signUp_terms_title_pleaseAgreeWithServiceTerms),
                style = LocalTypography.current.title1Semibold,
                color = LocalColorScheme.current.primaryBlack,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                ),
                text = stringResource(id = R.string.signUp_terms_subtitle_pleaseAgreeWithRegisterAndInformationProviding),
                style = LocalTypography.current.body1Regular,
                color = LocalColorScheme.current.secondary400,
            )
            Spacer(modifier = Modifier.height(32.dp))


            TermsCheckGroup(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = stringResource(id = R.string.signUp_terms_items_title_agreeAllWithOptionalInformation),
                    )
                },
                // TODO
                isChecked = false,
                onCheckAll = {},
                onItemCheck = {},
                checks = listOf(
                    TermsCheckItem(
                        id = "asdfa",
                        isChecked = false,
                        title = "[필수] 이용 약관 동의",
                        url = "ㅁㄴㅇㄹ",
                    ),
                    TermsCheckItem(
                        id = "asdfa",
                        isChecked = false,
                        title = "[필수] 이용 약관 동의",
                        url = "ㅁㄴㅇㄹ",
                    ),
                    TermsCheckItem(
                        id = "asdfa",
                        isChecked = true,
                        title = "[필수] 이용 약관 동의",
                        url = "ㅁㄴㅇㄹ",
                    ),
                    TermsCheckItem(
                        id = "asdfa",
                        isChecked = true,
                        title = "[필수] 이용 약관 동의",
                        url = "ㅁㄴㅇㄹ",
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
            onClick = onNext,
        ) {
            Text(text = stringResource(id = R.string.signUp_terms_button_next))
        }
    }
}

@Composable
private fun TermsCheckGroup(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    isChecked: Boolean,
    onCheckAll: (check: Boolean) -> Unit,
    onItemCheck: (id: String) -> Unit,
    checks: List<TermsCheckItem>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            // TITLE
            CompositionLocalProvider(
                value = LocalContentColor provides LocalColorScheme.current.primaryWhite,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .background(
                            color = LocalColorScheme.current.primaryBlack,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(all = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = {
                            onCheckAll(!isChecked)
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = stringResource(id = R.string.signUp_terms_cd_agreeAll),
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    ProvideTextStyle(
                        value = LocalTypography.current.body2Semibold.copy(
                            // TODO: color =
                            color = LocalColorScheme.current.primaryWhite,
                        ),
                        content = title,
                    )
                }
            }
        }
        items(checks) { check ->
            TermsCheckItem(
                title = { Text(text = check.title) },
                isChecked = check.isChecked,
                onCheck = { onItemCheck(check.id) },
                onClick = {
                    // TODO: open web terms
                },
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
                contentDescription = stringResource(id = R.string.signUp_terms_cd_agree),
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
                contentDescription = stringResource(id = R.string.signUp_terms_cd_enterTermsDescription),
            )
        }
    }
}

data class TermsCheckItem(
    val id: String,
    val isChecked: Boolean,
    val title: String,
    val url: String,
)
