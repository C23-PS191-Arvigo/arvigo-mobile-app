package id.arvigo.arvigobasecore.ui.feature.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.HomeProductRepository
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeProductRepository: HomeProductRepository
) : ViewModel() {

    val response: MutableState<HomeUiState> = mutableStateOf(HomeUiState.Empty)

    init {
        getHomeProduct()
    }


    fun getHomeProduct() = viewModelScope.launch {
        homeProductRepository.getHomeProduct()
            .onStart {
                response.value = HomeUiState.Loading
            }.catch {
                response.value = HomeUiState.Failure(it)
            }.collect {
                response.value = HomeUiState.Success(it)
            }
    }

}