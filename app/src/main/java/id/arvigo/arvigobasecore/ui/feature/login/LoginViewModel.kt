package id.arvigo.arvigobasecore.ui.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.AuthRepositoryImpl
import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepositoryImpl) : ViewModel() {

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            authRepository.login(request)
        }
    }

}