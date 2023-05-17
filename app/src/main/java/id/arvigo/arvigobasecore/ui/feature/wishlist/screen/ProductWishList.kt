package id.arvigo.arvigobasecore.ui.feature.wishlist.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@Composable
fun ProductWishlistScreen() {
    Text(text = "Product wishlist")
}

@Preview(showBackground = true)
@Composable
fun ProductWishlistPreview() {
    ArvigoBaseCoreTheme {
        ProductWishlistScreen()
    }
}