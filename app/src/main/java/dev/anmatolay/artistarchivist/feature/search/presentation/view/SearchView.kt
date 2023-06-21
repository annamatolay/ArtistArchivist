package dev.anmatolay.artistarchivist.feature.search.presentation.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.BuildConfig
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.core.exception.ApiException
import dev.anmatolay.artistarchivist.core.exception.InvalidInputException
import dev.anmatolay.artistarchivist.core.presentation.TextFieldState
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistIconButton
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistTextField
import dev.anmatolay.artistarchivist.core.presentation.components.BodyText
import dev.anmatolay.artistarchivist.core.presentation.components.ErrorText
import dev.anmatolay.artistarchivist.core.presentation.components.HeadLineText
import dev.anmatolay.artistarchivist.feature.search.presentation.state.SearchTextFieldState
import dev.anmatolay.artistarchivist.ui.theme.ArtistArchivistTheme
import timber.log.Timber

@Composable
@OptIn(ExperimentalAnimationApi::class)
internal fun AnimatedSearchView(
    searchState: TextFieldState,
    onSearchAction: ((input: String) -> Unit)? = null,
    onSearchContent: @Composable () -> Unit = {},
) {
    var arrangementState: Arrangement.Vertical by remember {
        mutableStateOf(Arrangement.Center)
    }
    val animationSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessMedium)

    AnimatedContent(
        targetState = arrangementState,
        transitionSpec = { ContentTransform(fadeIn(animationSpec), fadeOut(animationSpec)) }
    ) { arrangement ->
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = arrangement
        ) {
            AnimatedVisibility(visible = arrangementState == Arrangement.Center) {
                HeadLineText(
                    text = BuildConfig.APP_NAME,
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            SearchView(searchState = searchState) {
                if (searchState.isValid) {
                    arrangementState = Arrangement.Top
                    onSearchAction?.invoke(searchState.text)
                } else {
                    searchState.enableShowValidationError()
                }
            }
            onSearchContent()
        }
    }
}

@Composable
private fun SearchView(
    modifier: Modifier = Modifier,
    searchState: TextFieldState,
    onAction: () -> Unit,
) {
    Column(modifier = modifier) {
        ArtistArchivistTextField(
            modifier = Modifier
                .onFocusChanged { focusState ->
                    searchState.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        searchState.enableShowValidationError()
                    }
                },
            value = searchState.text,
            onValueChange = { searchState.text = it },
            label = { BodyText(R.string.search_input_label) },
            placeholder = { BodyText(R.string.search_input_placeholder, Modifier.alpha(0.5f)) },
            isError = searchState.showValidationError(),
            imeAction = ImeAction.Search,
            keyboardActions = KeyboardActions(onSearch = { onAction() }),
            trailingIcon = {
                if (searchState.isLoading) {
                    CircularProgressIndicator(Modifier.size(24.dp))
                } else {
                    if (searchState.text.isNotEmpty()) {
                        ArtistArchivistIconButton(imageVector = Icons.Default.Close) {
                            // Remove text from TextField when you press the 'X' icon
                            searchState.text = ""
                            searchState.disableShowValidationError()
                        }
                    } else {
                        ArtistArchivistIconButton(imageVector = Icons.Default.Search) {
                            onAction()
                        }
                    }
                }
            },
            isEnabled = !searchState.isLoading,
        )

        searchState.getTextFieldError()?.let { error ->
            Timber.e(error)
            when (error) {
                is InvalidInputException -> ErrorText(R.string.search_error_input)
                is ApiException -> ErrorText(R.string.search_error_api)
                else -> ErrorText(R.string.search_error_default)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    ArtistArchivistTheme { AnimatedSearchView(remember { SearchTextFieldState() }) }
}
