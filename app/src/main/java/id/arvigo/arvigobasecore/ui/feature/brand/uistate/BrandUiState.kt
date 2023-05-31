package id.arvigo.arvigobasecore.ui.feature.brand.uistate

import id.arvigo.arvigobasecore.data.source.network.response.brands.Brand
import id.arvigo.arvigobasecore.data.source.network.response.home_product.Recommendation
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeUiState

sealed class BrandUiState {
    class Success(val data: List<Brand>) : BrandUiState()
    class Failure(val error: Throwable) : BrandUiState()
    object Loading : BrandUiState()
    object Empty : BrandUiState()
}
