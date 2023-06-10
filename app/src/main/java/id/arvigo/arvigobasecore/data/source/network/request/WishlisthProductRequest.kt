package id.arvigo.arvigobasecore.data.source.network.request


import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class WishlisthProductRequest(
    @SerializedName("detail_product_marketplace_id")
    val detailProductMarketplaceId: Any?,
    @SerializedName("product_id")
    val productId: Int
)