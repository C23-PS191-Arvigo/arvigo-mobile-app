package id.arvigo.arvigobasecore.ui.feature.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.repository.AuthRepositoryImpl
import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.domain.model.TextFieldState
import id.arvigo.arvigobasecore.domain.usecase.LoginUseCase
import id.arvigo.arvigobasecore.ui.common.UiEvents
import id.arvigo.arvigobasecore.ui.feature.login.model.AuthState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import id.arvigo.arvigobasecore.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private var _loginState  = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState

    private val  _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value:String){
        _passwordState.value = passwordState.value.copy(text = value)
    }

    fun loginUser(){
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)

            val loginResult = loginUseCase(
                email = emailState.value.text,
                password = passwordState.value.text,
                role = "mobile-app"
            )

            _loginState.value = loginState.value.copy(isLoading = false)

            if (loginResult.emailError != null){
                _emailState.value=emailState.value.copy(error = loginResult.emailError)
            }
            if (loginResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = loginResult.passwordError)
            }

            when(loginResult.result){
                is Resource.Success->{
                    _eventFlow.emit(
                        UiEvents.NavigateEvent(Screen.Home.route)
                    )
                }
                is Resource.Error->{
                    UiEvents.SnackbarEvent(
                        loginResult.result.message ?: "Error!"
                    )
                }
                else -> {

                }
            }
        }
    }

}