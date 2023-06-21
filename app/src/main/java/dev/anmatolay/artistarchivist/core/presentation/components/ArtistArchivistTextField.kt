package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.ui.theme.gradientEndDark
import dev.anmatolay.artistarchivist.ui.theme.gradientEndLight
import dev.anmatolay.artistarchivist.ui.theme.gradientStartDark
import dev.anmatolay.artistarchivist.ui.theme.gradientStartLight

@Composable
fun ArtistArchivistTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    imeAction: ImeAction = KeyboardOptions.Default.imeAction,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    textFieldColors: TextFieldColors = ArtistArchivistTextFieldDefaults.transparentBorderColors(),
    isEnabled: Boolean = true,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(
                    brush =
                    if (isSystemInDarkTheme())
                        Brush.linearGradient(listOf(gradientStartDark, gradientEndDark))
                    else
                        Brush.linearGradient(listOf(gradientStartLight, gradientEndLight)),
                    width = 2.dp,
                ),
                shape = RoundedCornerShape(21.dp),
            ),
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        singleLine = true,
        colors = textFieldColors,
        enabled = isEnabled,
    )
}

@Immutable
object ArtistArchivistTextFieldDefaults {
    @Composable
    fun transparentBorderColors() = OutlinedTextFieldDefaults.colors(
        disabledBorderColor = Color.Transparent,
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        errorBorderColor = Color.Transparent
    )
}
