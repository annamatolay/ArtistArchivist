package dev.anmatolay.artistarchivist.feature.search.presentation.state

import dev.anmatolay.artistarchivist.core.exception.InvalidInputException
import dev.anmatolay.artistarchivist.core.presentation.TextFieldState
import dev.anmatolay.artistarchivist.core.presentation.textFieldStateSaver
import java.util.regex.Pattern

// Consider input valid if not contains any special characters, only letters, numbers and spaces
private const val SEARCH_VALIDATION_REGEX = "^[A-Za-z\\d ]+\$"

class SearchTextFieldState(input: String? = null) :
    TextFieldState(validator = ::isInputValid, onValidationError = ::validationError) {
    init {
        input?.let {
            text = it
        }
    }
}

private fun validationError(input: String): InvalidInputException {
    return InvalidInputException("Invalid input: $input")
}

private fun isInputValid(input: String): Boolean {
    return input.isNotBlank() && Pattern.matches(SEARCH_VALIDATION_REGEX, input)
}

val SearchTextFieldStateSaver = textFieldStateSaver(SearchTextFieldState())
