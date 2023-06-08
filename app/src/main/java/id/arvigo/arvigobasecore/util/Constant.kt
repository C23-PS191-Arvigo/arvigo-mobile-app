package id.arvigo.arvigobasecore.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constant {
    const val BASE_URL = "https://api.arvigo.site"
    const val AUTH_PREFERENCES = "AUTH_PREF"
    val ONBOARDING_KEY = booleanPreferencesKey("ONBOARDING_KEY")
    val AUTH_KEY = stringSetPreferencesKey("auth_key")
    val AUTH_ID = stringSetPreferencesKey("auth_id")

    //Pictures
    const val IMAGE_BCA = "https://marcopolis.net/images/stories/indonesia-report/2016/companies/Bank_Central_Asia.jpg"
    const val IMAGE_MATRIX = "https://media.wired.com/photos/5ca648a330f00e47fd82ae77/master/w_2560%2Cc_limit/Culture_Matrix_Code_corridor.jpg"
}