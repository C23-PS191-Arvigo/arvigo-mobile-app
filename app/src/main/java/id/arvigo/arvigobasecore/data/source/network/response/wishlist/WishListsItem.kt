package id.arvigo.arvigobasecore.data.source.network.response.wishlist

import com.google.gson.annotations.SerializedName

data class WishListsItem(
    @SerializedName("products")
    val products: List<WishListsProductsItem>,
    @SerializedName("stores")
    val stores: List<WishListsStoreItem>
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

data class WishListsStoreItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("merchant")
    val merchant: String,
    @SerializedName("store_type")
    val storeType: String,
    @SerializedName("address")
    val address: String?,
    @SerializedName("marketplace_name")
    val marketplaceName: String?,
    @SerializedName("marketplace_link")
    val marketplaceLink: String
)