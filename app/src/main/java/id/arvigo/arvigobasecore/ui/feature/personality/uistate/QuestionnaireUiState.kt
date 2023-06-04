package id.arvigo.arvigobasecore.ui.feature.personality.uistate

import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.QuestionnaireResponseItem

sealed class QuestionnaireUiState {
    class SuccessQuestionnaire(val data: QuestionnaireResponseItem) : QuestionnaireUiState()
    class Failure(val error: Throwable) : QuestionnaireUiState()
    object Loading : QuestionnaireUiState()
    object Empty : QuestionnaireUiState()
}
