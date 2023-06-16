package id.arvigo.arvigobasecore.ui.feature.product_detail

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.data.source.network.request.WishlisthProductRequest
import id.arvigo.arvigobasecore.ui.component.ProductImageSlider
import id.arvigo.arvigobasecore.ui.component.StatelessTopBar
import id.arvigo.arvigobasecore.ui.component.alert.AlertDialogCustomDesc
import id.arvigo.arvigobasecore.ui.component.lazy.ItemProduct
import id.arvigo.arvigobasecore.ui.feature.deepAR.DeepArActivity
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
    val isFavorite = remember {
        mutableStateOf(false)
    }

    val isButtonEnable = remember { mutableStateOf(false) }

    val isWishList = viewModel.isFavorite.value

    val lifecycle : Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                idState.value = productId
                viewModel.getProductDetail(productId = productId)
            }
        }
    }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier,
        topBar = {
            StatelessTopBar(
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
                title = "Produk Detail",
                actionIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Not available yet. Under development.", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
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
                    when (response.data.marketplaces) {
                        null -> {
                            isButtonEnable.value = false
                        }
                        else -> {
                            isButtonEnable.value = true
                        }
                    }
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
                                    IconButton(onClick = {
                                        val requestResult = WishlisthProductRequest(
                                        productId = response.data.id, detailProductMarketplaceId = null,
                                    )
                                        if (isWishList) {
                                            Log.d("ParsData", "${response.data.id}")
                                            viewModel.deleteWishlistProduct(productId = response.data.id )
                                            viewModel.checkFavoriteStatus(response.data.id.toString())
                                            isFavorite.value = false
                                        } else {
                                            Log.d("ParsData", "${response.data.id}")
                                            viewModel.addWishlistProduct(productId = response.data.id )
                                            viewModel.checkFavoriteStatus(response.data.id.toString())
                                            isFavorite.value = true
                                        }
                                    }) {
                                        if (isWishList) {
                                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "", tint = Color.Red)
                                        } else {
                                            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
                                        }
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
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                            .padding(horizontal = 15.dp, vertical = 4.dp)
                    ) {
                        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                        val context = LocalContext.current
                        val openDeepAR = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartActivityForResult()
                        ) {
                        }
                        Row(
                            modifier = Modifier
                                .width(itemSize),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            val openDialog = remember { mutableStateOf(false) }
                            if (openDialog.value) {
                                AlertDialogCustomDesc(
                                    openDialog = { openDialog.value = false },
                                    title = "Pilih varian",
                                    backButton = "Kembali",
                                    desc = {
                                        val variantList = response.data.variants
                                        LazyColumn() {
                                            items(variantList) {
                                                Button(
                                                    onClick = {
                                                        val link = it.linkAr
                                                        val intent = Intent(
                                                            context,
                                                            DeepArActivity::class.java
                                                        ).apply {
                                                            putExtra("linkAr", link)
                                                        }
                                                        openDeepAR.launch(intent)
                                                    },
                                                    modifier = Modifier.fillMaxWidth(),
                                                    shape = MaterialTheme.shapes.small
                                                ) {
                                                    val altName = if (it.name == "deepar"){
                                                        "Coba Varian Utama"
                                                    } else {it.name}
                                                    Text(text = altName)
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                            OutlinedButton(
                                enabled = isButtonEnable.value,
                                modifier = Modifier
                                    .width(itemSize)
                                    .height(48.dp),
                                onClick = {
                                    // TODO: ERROR WHEN CLICKED 
                                    navController.navigate(Screen.RecommendationStore.createRoute(idState.value))
                                },
                                shape = MaterialTheme.shapes.small,
                                border = BorderStroke(width = 2.dp, color = if (isButtonEnable.value) MaterialTheme.colorScheme.primary else Color.LightGray),
                            )
                            {
                                Text(text = "Toko", style = MaterialTheme.typography.titleMedium.copy(
                                    color = if (isButtonEnable.value) MaterialTheme.colorScheme.primary else Color.LightGray,
                                ))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                onClick = {
                                    openDialog.value = true
                                },
                                shape = MaterialTheme.shapes.small,
                            )
                            {
                                Text(text = "Coba AR", style = MaterialTheme.typography.titleMedium.copy(
                                    color = Color.White,
                                ))
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
        }
    }
}