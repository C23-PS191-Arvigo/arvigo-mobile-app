package id.arvigo.arvigobasecore.data.source.network.response.brands


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)