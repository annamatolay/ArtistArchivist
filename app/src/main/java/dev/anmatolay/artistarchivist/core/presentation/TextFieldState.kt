package dev.anmatolay.artistarchivist.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

abstract class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val onValidationError: (String) -> Exception
) {
    var text: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    private var error: Throwable? by mutableStateOf(null)
    // was the TextField ever focused
    var isFocusedDirty: Boolean by mutableStateOf(false)
    private var isFocused: Boolean by mutableStateOf(false)
    private var displayValidationError: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowValidationError() {
        // only show errors if the text was at least once focused
        if (isFocusedDirty) {
            displayValidationError = true
        }
    }

    fun disableShowValidationError() {
        displayValidationError = false
    }

    fun showValidationError() =
        !isValid && displayValidationError

    fun getTextFieldError(): Throwable? {
        return error ?: if (showValidationError()) {
            onValidationError(text)
        } else {
            null
        }
    }

    fun setTextFieldError(throwable: Throwable) {
        error = throwable
    }
}

fun textFieldStateSaver(state: TextFieldState) = listSaver<TextFieldState, Any>(
    save = { listOf(it.text, it.isFocusedDirty) },
    restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
        }
    }
)
