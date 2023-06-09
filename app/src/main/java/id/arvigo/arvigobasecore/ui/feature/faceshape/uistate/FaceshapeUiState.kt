package id.arvigo.arvigobasecore.ui.feature.faceshape.uistate

import id.arvigo.arvigobasecore.data.source.network.response.brands.Brand
import id.arvigo.arvigobasecore.data.source.network.response.faceshape.FaceshapeData
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState

sealed class FaceshapeUiState {

    class Success(val data: FaceshapeData) : FaceshapeUiState()
    class Failure(val error: Throwable) : FaceshapeUiState()
    object Loading : FaceshapeUiState()
    object Empty : FaceshapeUiState()

}
