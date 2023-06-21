package dev.anmatolay.artistarchivist.feature.search.presentation

import dev.anmatolay.artistarchivist.core.presentation.BaseViewModel
import dev.anmatolay.artistarchivist.domain.usecase.GetArtistsUseCase
import dev.anmatolay.artistarchivist.feature.search.presentation.state.SearchState
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SearchViewModel(
    dispatcher: CoroutineDispatcher,
    private val getArtistsUseCase: GetArtistsUseCase,
) : BaseViewModel<SearchState>(dispatcher) {

    override val state = SearchState()

    fun getArtists(input: String) {
        launchViewModelScopeWithLoading {
            val response = getArtistsUseCase(input)
            response.withContextAndLoading {
                state.data.postValue(response.body())
            }
        }
    }
}
