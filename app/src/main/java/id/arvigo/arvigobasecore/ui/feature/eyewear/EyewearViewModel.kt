package id.arvigo.arvigobasecore.ui.feature.eyewear

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.CategoryRepository
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EyewearViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val response: MutableState<EyewearUiState> = mutableStateOf(EyewearUiState.Empty)

    init {
        getEyewearCategory()
    }

    fun getEyewearCategory() = viewModelScope.launch {
        categoryRepository.getEyewearCategory()
            .onStart {
                response.value = EyewearUiState.Loading
            }.catch {
                response.value = EyewearUiState.Failure(it)
            }.collect {
                response.value = EyewearUiState.Success(it)
            }
    }

}