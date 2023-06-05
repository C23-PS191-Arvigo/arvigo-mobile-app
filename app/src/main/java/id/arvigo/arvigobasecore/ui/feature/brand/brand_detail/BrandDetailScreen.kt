package id.arvigo.arvigobasecore.ui.feature.brand.brand_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.ProductItemCard
import id.arvigo.arvigobasecore.ui.feature.brand.brand_detail.uistate.BrandDetailUiState
import id.arvigo.arvigobasecore.ui.feature.eyewear.EyewearViewModel
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun BrandDetailScreen(
    navController: NavController,
    brandId: String,
) {
    BrandDetailContent(
        navController = navController,
        brandId = brandId,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDetailContent(
    navController: NavController,
    brandId: String,
) {
    val viewModel: BrandDetailViewModel = getViewModel()

    val lifecycle : Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.getBrandDetail(brandId = brandId)
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Brand Detail")
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
            is BrandDetailUiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(180.dp),
                    state = LazyGridState(),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(it)
                ){
                    items(response.data){ data ->
                        ProductItemCard(
                            name = data.name ,
                            image = data.image,
                            brand = data.brand,
                            onClick = {
                                navController.navigate(Screen.ProductDetail.createRoute(data.id))
                            } )
                    }
                }
            }
            is BrandDetailUiState.Failure -> {
                Text(text = response.error.message ?: "Unknown Error")
            }
            BrandDetailUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            BrandDetailUiState.Empty -> {
                Text(text = "Empty Data")
            }
        }
    }
}