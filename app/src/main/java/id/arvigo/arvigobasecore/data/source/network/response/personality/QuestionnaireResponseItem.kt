package id.arvigo.arvigobasecore.data.source.network.response.personality


import com.google.gson.annotations.SerializedName

data class QuestionnaireResponseItem(
    @SerializedName("percentage_of_agreeable")
    val percentageOfAgreeable: Double,
    @SerializedName("percentage_of_conscientious")
    val percentageOfConscientious: Double,
    @SerializedName("percentage_of_extraversion")
    val percentageOfExtraversion: Double,
    @SerializedName("percentage_of_neurotic")
    val percentageOfNeurotic: Double,
    @SerializedName("percentage_of_openess")
    val percentageOfOpeness: Double
)