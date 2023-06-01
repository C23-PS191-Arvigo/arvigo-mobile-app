package id.arvigo.arvigobasecore.ui.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.ui.feature.login.model.AuthState
import id.arvigo.arvigobasecore.ui.feature.splash.uistate.InitialAuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authPreferences: AuthPreferences,
) : ViewModel() {

    private val _authState = MutableLiveData<InitialAuthState>()
    val authState: LiveData<InitialAuthState> = _authState

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        checkAuthToken()
    }

    fun checkAuthToken() {
        viewModelScope.launch {
            val authToken = authPreferences.getAuthToken()
            if (authToken != null) {
                _isUserLoggedIn.value = !authToken.isNullOrEmpty()
            }
        }
    }

}