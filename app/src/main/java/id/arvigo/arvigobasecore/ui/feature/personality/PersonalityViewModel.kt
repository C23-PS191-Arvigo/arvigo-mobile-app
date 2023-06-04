package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.PersonalityRepository
import id.arvigo.arvigobasecore.data.source.network.request.QuestionnaireRequestX
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.PersonalityUiState
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.QuestionnaireUiState
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

    private val _questionnaireCompleted = mutableStateOf(false)
    val questionnaireCompleted: State<Boolean>
        get() = _questionnaireCompleted

    private val getPersonalityCalled = mutableStateOf(false)


    val response: MutableState<PersonalityUiState> = mutableStateOf(PersonalityUiState.Empty)
    val questionnaireResponse: MutableState<QuestionnaireUiState> = mutableStateOf(QuestionnaireUiState.Empty)

    init {
//        viewModelScope.launch {
//            val response = personalityRepository.getPersonality()
//            _state.value = response.body()?.data ?: emptyList()
//        }
        if (!getPersonalityCalled.value) {
            getPersonality()
            getPersonalityCalled.value = true
        }
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

    fun submitQuestionnaire(request: QuestionnaireRequestX) = viewModelScope.launch {
        personalityRepository.submitQuestionnaire(request = request)
            .onStart {
                response.value = PersonalityUiState.Loading
            }.catch {
                response.value = PersonalityUiState.Failure(it)
            }.collect {
                response.value = PersonalityUiState.SuccessQuestion(it)
            }
    }
}