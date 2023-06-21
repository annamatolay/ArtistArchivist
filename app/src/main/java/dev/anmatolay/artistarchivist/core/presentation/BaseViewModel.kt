package dev.anmatolay.artistarchivist.core.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.anmatolay.artistarchivist.core.exception.ApiException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<State : UiState<*>>(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    abstract val state: State

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(
            throwable,
            "Exception handled: ${throwable.localizedMessage}"
        )
    }

    fun launchViewModelScopeWithLoading(
        context: CoroutineContext? = null,
        block: suspend CoroutineScope.() -> Unit,
    ) {
        state.loading.value = true
        if (context == null) {
            viewModelScope.launch(context = dispatcher + exceptionHandler, block = block)
        } else {
            viewModelScope.launch(context = context + exceptionHandler, block = block)
        }
    }

    suspend fun <T> Response<T>.withContextAndLoading(
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
        onError: () -> Unit = { onError(response = this@withContextAndLoading) },
        onSuccess: () -> Unit,
    ) = withContext(coroutineDispatcher) {
        if (this@withContextAndLoading.isSuccessful) {
            onSuccess()
            state.loading.value = false
        } else {
            onError()
        }
    }

    private fun onError(response: Response<*>) {
        val exception = ApiException(response)
        onError(exception, null)
    }

    private fun onError(throwable: Throwable, message: String?) {
        onError(throwable) {
            message?.let { Timber.e(it) }
        }
    }

    private fun onError(throwable: Throwable, onErrorLogging: () -> Unit = { Timber.e(throwable) }) {
        state.error.postValue(throwable)
        state.loading.postValue(false)
        onErrorLogging()
    }

    companion object {
        // Would be misleading to rename because this function doesn't contain any composable content
        @SuppressLint("ComposableNaming")
        @Composable
        fun <T> LiveData<T>.observe(observer: Observer<in T>) {
            observe(LocalLifecycleOwner.current, observer)
        }
    }
}
