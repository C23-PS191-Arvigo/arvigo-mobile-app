package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.PersonalityRepository
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalityViewModel(
    private val personalityRepository: PersonalityRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<PersonalityData>())
    val state: StateFlow<List<PersonalityData>>
        get() = _state

    init {
        viewModelScope.launch {
            val response = personalityRepository.getPersonality()
            _state.value = response.body()?.data ?: emptyList()
        }
    }
}