package id.arvigo.arvigobasecore.ui.feature.makeup.uistate

import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryItem
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState

sealed class MakeupUiState {

    class Success(val data: List<CategoryItem>) : MakeupUiState()
    class Failure(val error: Throwable) : MakeupUiState()
    object Loading : MakeupUiState()
    object Empty : MakeupUiState()

}
