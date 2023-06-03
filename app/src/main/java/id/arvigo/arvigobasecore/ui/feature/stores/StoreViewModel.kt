package id.arvigo.arvigobasecore.ui.feature.stores

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.StoreRepository
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState
import id.arvigo.arvigobasecore.ui.feature.stores.uistate.StoreUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class StoreViewModel(private val storeRepository: StoreRepository) : ViewModel() {

    val response: MutableState<StoreUiState> = mutableStateOf(StoreUiState.Empty)

    init {
        getStores()
    }

    fun getStores() = viewModelScope.launch {
        storeRepository.getStores()
            .onStart {
                response.value = StoreUiState.Loading
            }.catch {
                response.value = StoreUiState.Failure(it)
            }.collect {
                response.value = StoreUiState.Success(it)
            }
    }

}