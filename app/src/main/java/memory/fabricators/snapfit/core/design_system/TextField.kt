package memory.fabricators.snapfit.core.design_system

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintValue: String? = null,
    maxLines: Int = 1,
) = androidx.compose.material3.OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    shape = RoundedCornerShape(5.dp),
    placeholder = if (hintValue != null) {
        { Text(text = hintValue) }
    } else {
        null
    },
    colors = TextFieldDefaults.colors(),
    maxLines = maxLines,
)

object TextFieldDefaults {
    @Composable
    fun colors(
        focusedTextColor: Color = LocalColorScheme.current.primaryBlack,
        unfocusedTextColor: Color = LocalColorScheme.current.primaryBlack,
        errorTextColor: Color = LocalColorScheme.current.accentPink,
        cursorColor: Color = LocalColorScheme.current.primaryBlack,
        focusedBorderColor: Color = LocalColorScheme.current.primaryBlack,
        unfocusedBorderColor: Color = LocalColorScheme.current.secondary200,
        errorBorderColor: Color = LocalColorScheme.current.accentPink,
        focusedLeadingIconColor: Color = LocalColorScheme.current.primaryBlack,
        unfocusedLeadingIconColor: Color = LocalColorScheme.current.secondary300,
        focusedTrailingIconColor: Color = Color.Unspecified,
        unfocusedTrailingIconColor: Color = Color.Unspecified,
        placeholderColor: Color = Color.Unspecified,
    ): TextFieldColors =
        OutlinedTextFieldDefaults.colors().copy(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            errorTextColor = errorTextColor,
            cursorColor = cursorColor,
            focusedIndicatorColor = focusedBorderColor,
            unfocusedIndicatorColor = unfocusedBorderColor,
            errorIndicatorColor = errorBorderColor,
            focusedLeadingIconColor = focusedLeadingIconColor,
            unfocusedLeadingIconColor = unfocusedLeadingIconColor,
            focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            focusedPlaceholderColor = placeholderColor,
        )
}
