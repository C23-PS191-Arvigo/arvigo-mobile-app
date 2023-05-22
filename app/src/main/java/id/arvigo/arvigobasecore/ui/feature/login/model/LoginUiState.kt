package id.arvigo.arvigobasecore.ui.feature.login.model

import id.arvigo.arvigobasecore.domain.model.User
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterRequest

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Loading : LoginUiState
    object Empty : LoginUiState

    data class Success(val data: RegisterRequest) : LoginUiState

    data class Error(val error: RegisterRequest) : LoginUiState
}