package id.arvigo.arvigobasecore.ui.feature.wishlist.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.arvigo.arvigobasecore.ui.component.ProductLazyGrid
import id.arvigo.arvigobasecore.ui.feature.wishlist.model.FakeProductData.dummyProduct
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@Composable
fun ProductWishlistScreen() {
    ProductLazyGrid(itemList = dummyProduct)
}

@Preview(showBackground = true)
@Composable
fun ProductWishlistPreview() {
    ArvigoBaseCoreTheme {
        ProductWishlistScreen()
    }
}