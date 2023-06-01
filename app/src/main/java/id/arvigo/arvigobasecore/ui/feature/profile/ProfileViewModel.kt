package id.arvigo.arvigobasecore.ui.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    fun logout() {
       viewModelScope.launch {
           authPreferences.clearAuthToken()
       }
    }

}