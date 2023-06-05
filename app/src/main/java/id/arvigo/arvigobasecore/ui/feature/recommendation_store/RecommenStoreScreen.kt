package id.arvigo.arvigobasecore.ui.feature.recommendation_store

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.feature.recommendation_store.uistate.RecommenStoreUiState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun RecommenStoreScreen(
    navController: NavController,
    productId: String,
) {
    RecommenStoreContent(
        navController = navController,
        productId = productId,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommenStoreContent(
    navController: NavController,
    productId: String,
) {

    val viewModel: RecommenStoreViewModel = getViewModel()
    val lifecycle : Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.getProductStore(productId = "4")
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Rekomendasi Toko")
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
        val response = viewModel.response.value

        when(response) {
            is RecommenStoreUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(it)
                ) {
                   items(response.data) { item ->
                       RecommenStoreCard(name = item.name, image = item.image, price = item.price.toString(), storeName = item.merchant, brand = item.brand)
                   }
                }
            }
            is RecommenStoreUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            is RecommenStoreUiState.Failure -> {
                Text(text = response.error.message ?: "Unknown Error")
            }
            RecommenStoreUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Empty", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommenStoreCard(
    name: String,
    image: String,
    price: String,
    storeName: String,
    brand: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(177.dp)
            .padding(bottom = 12.dp),
    ) {
       Row() {
           AsyncImage(
               model = ImageRequest.Builder(LocalContext.current)
                   .data(image)
                   .placeholder(R.drawable.img_placeholder)
                   .crossfade(true)
                   .build(),
               contentDescription = null,
               contentScale = ContentScale.Crop,
               placeholder = painterResource(id = R.drawable.img_placeholder),
               modifier = Modifier
                   .width(170.dp)
                   .height(177.dp)
           )
           Spacer(modifier = Modifier.width(12.dp))
           Column(
                modifier = Modifier
                     .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
           ) {
               Text(
                   text = name,
                   style = MaterialTheme.typography.titleLarge,
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis,
               )
               Spacer(modifier = Modifier.height(8.dp))
               Row {
                   Icon(
                       imageVector = Icons.Default.ShoppingBag,
                       contentDescription = "",
                       modifier = Modifier.size(16.dp),
                       tint = Color.Green,
                   )
                   Spacer(modifier = Modifier.height(2.dp))
                   Text(text = storeName, style = MaterialTheme.typography.bodyMedium )
               }
               Spacer(modifier = Modifier.height(8.dp))
               Text(text = "Rp. $price", style = MaterialTheme.typography.headlineSmall.copy(
                   fontWeight = FontWeight.SemiBold
               ))
               Spacer(modifier = Modifier.height(6.dp))
               Text(text = brand, style = MaterialTheme.typography.bodyMedium.copy(
                   color = Color.Gray,
               ))
           }
       }
    }
}


@Preview
@Composable
fun TestPrev() {

}