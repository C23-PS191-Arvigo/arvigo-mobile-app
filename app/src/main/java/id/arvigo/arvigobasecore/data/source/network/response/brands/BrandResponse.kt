package id.arvigo.arvigobasecore.data.source.network.response.brands


import com.google.gson.annotations.SerializedName

data class BrandResponse(
    @SerializedName("data")
    val `data`: List<Brand>,
    @SerializedName("message")
    val message: String
)