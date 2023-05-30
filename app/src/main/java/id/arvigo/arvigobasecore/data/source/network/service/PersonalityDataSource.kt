package id.arvigo.arvigobasecore.data.source.network.service

import id.arvigo.arvigobasecore.data.source.network.ApiService

class PersonalityDataSource(private val apiService: ApiService) {

    suspend fun getPersonality() = apiService.getQuestionnaires(
        token = ""
    )

}