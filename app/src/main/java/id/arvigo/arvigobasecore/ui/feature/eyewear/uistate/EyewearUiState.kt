package id.arvigo.arvigobasecore.ui.feature.eyewear.uistate

import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryItem

sealed class EyewearUiState {
    class Success(val data: List<CategoryItem>) : EyewearUiState()
    class Failure(val error: Throwable) : EyewearUiState()
    object Loading : EyewearUiState()
    object Empty : EyewearUiState()
}
