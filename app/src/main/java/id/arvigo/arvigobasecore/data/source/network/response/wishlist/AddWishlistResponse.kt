package id.arvigo.arvigobasecore.data.source.network.response.wishlist


import com.google.gson.annotations.SerializedName

data class AddWishlistResponse(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("message")
    val message: String
)