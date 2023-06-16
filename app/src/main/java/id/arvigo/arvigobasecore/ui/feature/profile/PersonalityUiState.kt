package id.arvigo.arvigobasecore.ui.feature.profile

sealed class PersonalityUiState {
    class Success(val data: PersonalityResponseItem) : PersonalityUiState()
    class Failure(val error: Throwable) : PersonalityUiState()
    object Loading : PersonalityUiState()
    object Empty : PersonalityUiState()
}
