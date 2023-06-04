package id.arvigo.arvigobasecore.data.source.network.response.personality


import com.google.gson.annotations.SerializedName

data class QuestionnaireResponse(
    @SerializedName("data")
    val `data`: QuestionnaireResponseItem,
    @SerializedName("message")
    val message: String
)