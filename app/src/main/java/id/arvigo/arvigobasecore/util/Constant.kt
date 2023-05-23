package id.arvigo.arvigobasecore.util

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constant {
    const val BASE_URL = "https://api.arvigo.site"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
}