package id.arvigo.arvigobasecore.data.source.network.response.home_product


import com.google.gson.annotations.SerializedName

data class HomeProduct(
    @SerializedName("data")
    val `data`: DataHomeProduct,
    @SerializedName("message")
    val message: String
)