package id.arvigo.arvigobasecore.data.repository

import android.util.Log
import id.arvigo.arvigobasecore.data.source.local.AuthPreferences
import id.arvigo.arvigobasecore.data.source.network.ApiService
import id.arvigo.arvigobasecore.data.source.network.request.QuestionnaireRequestX
import id.arvigo.arvigobasecore.data.source.network.response.personality.Personality
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import id.arvigo.arvigobasecore.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.Flow

class PersonalityRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences,
) {

    fun getPersonality() = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Question", "getPersonality")
        emit(apiService.getQuestionnaires(
            token = "Bearer $token"
        ).data)
    }.flowOn(Dispatchers.IO)


    fun submitQuestionnaire(request: QuestionnaireRequestX) = flow {
        val token = authPreferences.getAuthToken()
        Log.d("Hit API Question", "submit Questionnaire")
        emit(apiService.postQuestionnaire(
            token = "Bearer $token",
            request = request
        ).data)
    }

}