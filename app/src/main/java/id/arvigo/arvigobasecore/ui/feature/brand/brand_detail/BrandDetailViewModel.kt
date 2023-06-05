package id.arvigo.arvigobasecore.ui.feature.brand.brand_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.BrandRepository
import id.arvigo.arvigobasecore.ui.feature.brand.brand_detail.uistate.BrandDetailUiState
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BrandDetailViewModel(
    private val brandRepository: BrandRepository
) : ViewModel() {

    val response: MutableState<BrandDetailUiState> = mutableStateOf(BrandDetailUiState.Empty)

    fun getBrandDetail(brandId: String) = viewModelScope.launch {
        brandRepository.getBrandDetail(brandId = brandId)
            .onStart {
                response.value = BrandDetailUiState.Loading
            }.catch {
                response.value = BrandDetailUiState.Failure(it)
            }.collect {
                response.value = BrandDetailUiState.Success(it)
            }
    }

}