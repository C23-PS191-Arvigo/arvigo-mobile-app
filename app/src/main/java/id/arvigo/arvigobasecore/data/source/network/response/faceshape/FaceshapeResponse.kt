package id.arvigo.arvigobasecore.data.source.network.response.faceshape


import com.google.gson.annotations.SerializedName

data class FaceshapeResponse(
    @SerializedName("data")
    val `data`: FaceshapeData,
    @SerializedName("message")
    val message: String
)