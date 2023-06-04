package id.arvigo.arvigobasecore.data.source.network.response.wishlist

import com.google.gson.annotations.SerializedName

data class WishListsItem(
    @SerializedName("products")
    val products: List<WishListsProductsItem>,
    /*    @SerializedName("stores")
        val stores*/
)

data class WishListsProductsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("brand")
    val brand: String
)