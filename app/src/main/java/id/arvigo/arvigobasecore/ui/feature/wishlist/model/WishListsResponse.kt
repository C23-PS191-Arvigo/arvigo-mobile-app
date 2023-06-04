package id.arvigo.arvigobasecore.ui.feature.wishlist.model

import com.google.gson.annotations.SerializedName
import id.arvigo.arvigobasecore.data.source.network.response.wishlist.WishListsItem

data class WishListsResponse (
    @SerializedName("data")
    val `data`: WishListsItem,
    @SerializedName("message")
    val message: String
)