package id.arvigo.arvigobasecore.ui.feature.faceshape

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.FaceshapeRepository
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState
import id.arvigo.arvigobasecore.ui.feature.faceshape.uistate.FaceshapeUiState
import id.arvigo.arvigobasecore.util.reduceFileImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class FaceShapeViewModel(
    private val faceshapeRepository: FaceshapeRepository,
) : ViewModel() {

    val response: MutableState<FaceshapeUiState> = mutableStateOf(FaceshapeUiState.Empty)

    fun submitFaceShape(image: File) = viewModelScope.launch {
        val imageSized = withContext(Dispatchers.IO) {
            reduceFileImage(image)
        }
        faceshapeRepository.submitFaceShape(
            image = imageSized
        )
            .onStart {
                response.value = FaceshapeUiState.Loading
            }.catch {
                response.value = FaceshapeUiState.Failure(it)
            }.collect {
                response.value = FaceshapeUiState.Success(it)
                Log.d("FaceShapeViewModel", "Result: ${it.result}")
            }
        image.delete()
    }

}