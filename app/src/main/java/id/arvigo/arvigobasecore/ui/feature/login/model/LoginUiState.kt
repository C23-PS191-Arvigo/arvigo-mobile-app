package id.arvigo.arvigobasecore.ui.feature.login.model

import id.arvigo.arvigobasecore.domain.model.User

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    object Empty : LoginUiState

    data class Success(val data: List<User>) : LoginUiState

    data class Error(val error: String) : LoginUiState
}