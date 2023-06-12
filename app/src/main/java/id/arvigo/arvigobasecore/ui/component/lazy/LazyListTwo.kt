package id.arvigo.arvigobasecore.ui.component.lazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.data.source.network.response.wishlist.WishListsProductsItem
import id.arvigo.arvigobasecore.ui.component.cards.ItemListTwo

@Composable
fun ProductLazyGridTwo(
    navController: NavController,
    itemList: List<WishListsProductsItem>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        state = LazyGridState(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(itemList){
            ItemListTwo(navController = navController, id = it.id, image = it.image, name = it.name, brand = it.brand)
        }
    }
}