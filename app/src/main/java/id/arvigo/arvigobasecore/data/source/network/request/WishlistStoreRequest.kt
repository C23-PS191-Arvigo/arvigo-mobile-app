package id.arvigo.arvigobasecore.data.source.network.request

import com.google.gson.annotations.SerializedName

data class WishlistStoreRequest(
        @SerializedName("detail_product_marketplace_id")
        val detailProductMarketplaceId: Int,
        @SerializedName("product_id")
        val productId: Any?
)
