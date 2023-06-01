package id.arvigo.arvigobasecore.data.source.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import id.arvigo.arvigobasecore.util.Constant.AUTH_KEY
import kotlinx.coroutines.flow.first

class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun saveAuthToken(loginToken:String){
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(loginToken)
        }
        Log.d("AuthPreferences", "saveAuthToken: $loginToken")
    }

    suspend fun getAuthToken(): String? {
        val preferences = dataStore.data.first()
        val token = preferences[AUTH_KEY]?.firstOrNull()
        Log.d("AuthPreferences", "getAuthToken: $token")
        return token
    }

}