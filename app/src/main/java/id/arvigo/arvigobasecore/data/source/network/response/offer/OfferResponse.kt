package id.arvigo.arvigobasecore.data.source.network.response.offer


import com.google.gson.annotations.SerializedName

data class OfferResponse(
        @SerializedName("data")
    val `data`: OfferItem,
        @SerializedName("message")
    val message: String
)