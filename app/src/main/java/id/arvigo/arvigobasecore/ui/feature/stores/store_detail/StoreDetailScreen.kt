package id.arvigo.arvigobasecore.ui.feature.stores.store_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.data.source.network.response.stores.StoreDataItem
import id.arvigo.arvigobasecore.ui.feature.recommendation_store.RecommenStoreCard
import id.arvigo.arvigobasecore.ui.navigation.Screen

@Composable
fun StoreDetail(
    navController: NavController,
    storeDataItem: List<StoreDataItem>
) {
    StoreDetailContent(
        navController = navController,
        storeDataItem = storeDataItem,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDetailContent(
    navController: NavController,
    storeDataItem: List<StoreDataItem>
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Detail Toko")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                        )
                    }
                },
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            items(storeDataItem) { item ->
                RecommenStoreCard(name = item.name, image = item.image, price = item.price.toString(), storeName = item.merchant, brand = item.brand, onClick = {
                    navController.navigate(Screen.OfferDetail.createRoute(item.id))
                })
            }
        }
    }
}