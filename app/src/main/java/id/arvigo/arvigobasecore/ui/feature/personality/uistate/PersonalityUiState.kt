package id.arvigo.arvigobasecore.ui.feature.personality.uistate

import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.QuestionnaireResponseItem

sealed class PersonalityUiState {
    class Success(val data: List<PersonalityData>) : PersonalityUiState()
    class SuccessQuestion(val data: QuestionnaireResponseItem) : PersonalityUiState()
    class Failure(val error: Throwable) : PersonalityUiState()
    object Loading : PersonalityUiState()
    object Empty : PersonalityUiState()
}
