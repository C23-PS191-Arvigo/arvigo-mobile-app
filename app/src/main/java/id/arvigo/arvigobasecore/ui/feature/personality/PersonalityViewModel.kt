package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.PersonalityRepository
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.PersonalityUiState
import id.arvigo.arvigobasecore.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PersonalityViewModel(
    private val personalityRepository: PersonalityRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<PersonalityData>())
    val state: StateFlow<List<PersonalityData>>
        get() = _state

    val response: MutableState<PersonalityUiState> = mutableStateOf(PersonalityUiState.Empty)

    init {
//        viewModelScope.launch {
//            val response = personalityRepository.getPersonality()
//            _state.value = response.body()?.data ?: emptyList()
//        }
        getPersonality()
    }

    fun getPersonality() = viewModelScope.launch {
        personalityRepository.getPersonality()
            .onStart {
                response.value = PersonalityUiState.Loading
            }.catch {
                response.value = PersonalityUiState.Failure(it)
            }.collect {
                response.value = PersonalityUiState.Success(it)
            }
    }
}