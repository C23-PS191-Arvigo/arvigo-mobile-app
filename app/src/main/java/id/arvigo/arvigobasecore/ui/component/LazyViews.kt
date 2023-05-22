package id.arvigo.arvigobasecore.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.feature.wishlist.model.Product
import id.arvigo.arvigobasecore.ui.feature.wishlist.screen.ProductWishlistScreen
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@Composable
fun ProductLazyGrid(
    itemList: List<Product>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        state = LazyGridState(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(itemList){
            ItemProduct(image = it.image, name = it.title, store = it.store)
        }
    }
}

@Composable
fun StoreLazyGrid(
    itemList: List<Product>
) {
    LazyColumn(
        state = LazyListState(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(itemList){
            ItemBrand(image = it.image, name = it.title, store = it.store)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemProduct(
    image: String,
    name: String,
    store: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        ) {
            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(image)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = R.drawable.img_placeholder),
                        alignment = Alignment.Center,
                    )
                }
                Spacer(modifier = Modifier.padding(top = 2.dp))
                Text(text = store, style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 10.dp))
                Spacer(modifier = Modifier.padding(top = 2.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.padding(top = 2.dp))
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemBrand(
    image: String,
    name: String,
    store: String
) {
    Card() {
        Row(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .padding(horizontal = 4.dp, vertical = 4.dp),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img_placeholder),
                    alignment = Alignment.Center,
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = store, style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 10.dp))
                Text(text = store, style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 10.dp))
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ArvigoBaseCoreTheme {
        ItemBrand(image = "", name = "hello", store = "store")
    }
}