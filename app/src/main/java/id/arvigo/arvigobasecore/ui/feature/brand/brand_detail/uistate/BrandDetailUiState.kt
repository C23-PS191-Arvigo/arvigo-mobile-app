package id.arvigo.arvigobasecore.ui.feature.brand.brand_detail.uistate

import id.arvigo.arvigobasecore.data.source.network.response.category.CategoryItem

sealed class BrandDetailUiState {

    class Success(val data: List<CategoryItem>) : BrandDetailUiState()
    class Failure(val error: Throwable) : BrandDetailUiState()
    object Loading : BrandDetailUiState()
    object Empty : BrandDetailUiState()

}
