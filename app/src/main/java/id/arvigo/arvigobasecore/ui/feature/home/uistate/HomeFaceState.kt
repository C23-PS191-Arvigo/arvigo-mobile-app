package id.arvigo.arvigobasecore.ui.feature.home.uistate

import id.arvigo.arvigobasecore.data.source.network.response.home_product.Recommendation

sealed class HomeFaceState {
    class Success(val data: List<Recommendation>?) : HomeFaceState()
    class Failure(val error: Throwable) : HomeFaceState()
    object Loading : HomeFaceState()
    object Empty : HomeFaceState()
}