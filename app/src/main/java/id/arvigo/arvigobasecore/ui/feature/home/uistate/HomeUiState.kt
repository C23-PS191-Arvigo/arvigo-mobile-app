package id.arvigo.arvigobasecore.ui.feature.home.uistate

import id.arvigo.arvigobasecore.data.source.network.response.home_product.Recommendation
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData

sealed class HomeUiState {
    class Success(val data: List<Recommendation>) : HomeUiState()
    class Failure(val error: Throwable) : HomeUiState()
    object Loading : HomeUiState()
    object Empty : HomeUiState()
}
