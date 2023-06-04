package id.arvigo.arvigobasecore.ui.feature.wishlist.model

import id.arvigo.arvigobasecore.data.source.network.response.wishlist.Product

data class ProductList(
    val product: Product,
    val count: Int
)
