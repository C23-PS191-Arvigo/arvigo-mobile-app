package id.arvigo.arvigobasecore.ui.feature.product_detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import id.arvigo.arvigobasecore.ui.component.ProductImageSlider
import id.arvigo.arvigobasecore.ui.feature.product_detail.uistate.ProductDetailUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String,
) {
    ProductDetailContent(
        navController = navController,
        productId = productId,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailContent(
    navController: NavController,
    productId: String,
) {

    val viewModel: ProductDetailViewModel = getViewModel()
    val idState = remember { mutableStateOf("") }

    val lifecycle : Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = productId
                viewModel.getProductDetail(productId = productId)
            }
        }
    }

    Scaffold(

        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Produk Detail")
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
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "")
                    }
                }
            )
        }

    ) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            val response = viewModel.response.value

            when(response) {
                is ProductDetailUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                is ProductDetailUiState.Success -> {
                   LazyColumn(
                       modifier = Modifier
                           .fillMaxWidth()
                           .weight(3.8f)
                   ) {
                       item {
                            ProductImageSlider(
                                imageData = response.data.images,
                            )
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 16.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(text = response.data.name, style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.SemiBold,
                                    ))
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
                                    }
                                }
                                Text(text = response.data.brandName, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(32.dp))
                                Text(text = "Deskripsi", style = MaterialTheme.typography.titleLarge)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = response.data.description, style = MaterialTheme.typography.bodyLarge)
                            }
                       }
                   }
                }
                is ProductDetailUiState.Failure -> {
                    Text(text = response.error.message ?: "Unknown Error")
                }
                ProductDetailUiState.Empty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3.8f)
                    ) {
                        Text(text = "Empty", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(horizontal = 15.dp, vertical = 4.dp)
            ) {
                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                Row(
                    modifier = Modifier
                        .width(itemSize),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .width(itemSize),
                        onClick = {
                                  navController.navigate(Screen.RecommendationStore.createRoute(idState.value))
                        },
                        shape = MaterialTheme.shapes.small,
                        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
                    )
                    {
                        Text(text = "Toko", style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                        ))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { /*TODO*/ },
                        shape = MaterialTheme.shapes.small,
                    )
                    {
                        Text(text = "Coba dengan AR", style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                        ))
                    }
                }
            }
        }
    }
}