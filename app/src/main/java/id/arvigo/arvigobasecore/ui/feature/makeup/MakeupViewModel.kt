package id.arvigo.arvigobasecore.ui.feature.makeup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.CategoryRepository
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState
import id.arvigo.arvigobasecore.ui.feature.makeup.uistate.MakeupUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MakeupViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    val response: MutableState<MakeupUiState> = mutableStateOf(MakeupUiState.Empty)

    init {
       getMakeupCategory()
    }

    fun getMakeupCategory() = viewModelScope.launch {
        categoryRepository.getMakeupCategory()
            .onStart {
                response.value = MakeupUiState.Loading
            }.catch {
                response.value = MakeupUiState.Failure(it)
            }.collect {
                response.value = MakeupUiState.Success(it)
            }
    }

}