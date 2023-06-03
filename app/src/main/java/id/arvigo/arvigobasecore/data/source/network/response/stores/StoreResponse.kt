package id.arvigo.arvigobasecore.data.source.network.response.stores


import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("data")
    val `data`: List<StoreData>,
    @SerializedName("message")
    val message: String
)