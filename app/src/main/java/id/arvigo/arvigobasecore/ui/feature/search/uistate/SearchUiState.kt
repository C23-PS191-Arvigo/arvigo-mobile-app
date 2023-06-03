package id.arvigo.arvigobasecore.ui.feature.search.uistate

import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryItem
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState

sealed class SearchUiState {
    class Success(val data: List<CategoryItem>) : SearchUiState()
    class Failure(val error: Throwable) : SearchUiState()
    object Loading : SearchUiState()
    object Empty : SearchUiState()
}
