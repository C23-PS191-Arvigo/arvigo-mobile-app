package id.arvigo.arvigobasecore.ui.feature.brand

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.BrandRepository
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BrandViewModel(
    private val brandRepository: BrandRepository
) : ViewModel() {

    val response: MutableState<BrandUiState> = mutableStateOf(BrandUiState.Empty)

    init {
        getBrand()
    }

    fun getBrand() = viewModelScope.launch {
        brandRepository.getBrand()
            .onStart {
                response.value = BrandUiState.Loading
            }.catch {
                response.value = BrandUiState.Failure(it)
            }.collect {
                response.value = BrandUiState.Success(it)
            }
    }

}