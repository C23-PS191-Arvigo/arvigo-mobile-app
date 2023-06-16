package id.arvigo.arvigobasecore.ui.feature.home.uistate

import id.arvigo.arvigobasecore.data.source.network.response.home_product.DataHomeProduct
import id.arvigo.arvigobasecore.data.source.network.response.home_product.Recommendation

sealed class HomePersonalState {

    class Success(val data: List<Recommendation>?) : HomePersonalState()
    class SuccessPersonal(val data: DataHomeProduct) : HomePersonalState()
    class Failure(val error: Throwable) : HomePersonalState()
    object Loading : HomePersonalState()
    object Empty : HomePersonalState()

}