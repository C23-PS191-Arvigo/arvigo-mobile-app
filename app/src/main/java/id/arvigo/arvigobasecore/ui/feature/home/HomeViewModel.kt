package id.arvigo.arvigobasecore.ui.feature.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.HomeProductRepository
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeFaceState
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomePersonalState
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeProductRepository: HomeProductRepository
) : ViewModel() {

    val response: MutableState<HomeUiState> = mutableStateOf(HomeUiState.Empty)
    val responsePersonal: MutableState<HomePersonalState> = mutableStateOf(HomePersonalState.Empty)
    val responseFace: MutableState<HomeFaceState> = mutableStateOf(HomeFaceState.Empty)

    init {
        getHomeProduct()
        getHomePersonal()
        getHomeFace()
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

    fun getHomePersonal() = viewModelScope.launch {
        homeProductRepository.getHomePersonality()
                .onStart {
                    responsePersonal.value = HomePersonalState.Loading
                }.catch {
                    responsePersonal.value = HomePersonalState.Failure(it)
                }.collect {
                    responsePersonal.value = HomePersonalState.Success(it ?: emptyList())
                }
    }

    fun getHomeFace() = viewModelScope.launch {
        homeProductRepository.getHomeFace()
                .onStart {
                    responseFace.value = HomeFaceState.Loading
                }.catch {
                    responseFace.value = HomeFaceState.Failure(it)
                }.collect {
                    responseFace.value = HomeFaceState.Success(it ?: emptyList())
                }
    }

}