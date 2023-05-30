package id.arvigo.arvigobasecore.ui.feature.personality.uistate

import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData

sealed class PersonalityUiState {
    class Success(val data: List<PersonalityData>) : PersonalityUiState()
    class Failure(val error: Throwable) : PersonalityUiState()
    object Loading : PersonalityUiState()
    object Empty : PersonalityUiState()
}
