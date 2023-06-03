package id.arvigo.arvigobasecore.ui.feature.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.SearchProductRepository
import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryItem
import id.arvigo.arvigobasecore.domain.model.TextFieldState
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState
import id.arvigo.arvigobasecore.ui.feature.search.uistate.SearchUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProductRepository: SearchProductRepository
): ViewModel() {

    val response: MutableState<SearchUiState> = mutableStateOf(SearchUiState.Empty)

    private val _queryState = mutableStateOf(TextFieldState())
    val queryState: State<TextFieldState> = _queryState

    fun setQuery(value:String){
        _queryState.value = queryState.value.copy(text = value)
    }

    init {
        searchProduct()
    }

    fun searchProduct() = viewModelScope.launch {
        searchProductRepository.searchProduct(query = queryState.value.text)
            .onStart {
                response.value = SearchUiState.Loading
            }.catch {
                response.value = SearchUiState.Failure(it)
            }.collect {
                response.value = SearchUiState.Success(it)
                Log.d("SEARCH SUCCESS", "get Product")
            }
    }

}