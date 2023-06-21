package dev.anmatolay.artistarchivist.core.presentation

import androidx.lifecycle.MutableLiveData

abstract class UiState<T>(
    val data: MutableLiveData<T> = MutableLiveData<T>(),
    val loading: MutableLiveData<Boolean> = MutableLiveData(false),
    val error: MutableLiveData<Throwable> = MutableLiveData<Throwable>(),
)