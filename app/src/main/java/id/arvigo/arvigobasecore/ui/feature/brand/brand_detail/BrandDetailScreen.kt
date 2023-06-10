package id.arvigo.arvigobasecore.ui.feature.brand.brand_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
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
    brandLogo: String,
    brandName: String,
) {
    BrandDetailContent(
        navController = navController,
        brandId = brandId,
        brandLogo = brandLogo,
        brandName = brandName,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDetailContent(
    navController: NavController,
    brandId: String,
    brandLogo: String,
    brandName: String,
) {
    val viewModel: BrandDetailViewModel = getViewModel()

    val lifecycle : Lifecycle = LocalLifecycleOwner.current.lifecycle
    val logo = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                logo.value = brandLogo
                name.value = brandName
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
               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(it)
               ) {
                   Surface(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(horizontal = 16.dp)
                           .weight(0.3f)
                   ) {
                       Row(
                           modifier = Modifier
                               .fillMaxWidth()
                               .height(45.dp),
                            verticalAlignment = Alignment.CenterVertically,
                       ) {
                           AsyncImage(
                               model = ImageRequest.Builder(LocalContext.current)
                                   .data(brandLogo)
                                   .crossfade(true)
                                   .build(),
                               contentDescription = null,
                               contentScale = ContentScale.Crop,
                               placeholder = painterResource(id = R.drawable.img_placeholder),
                               modifier = Modifier
                                   .size(45.dp)
                                   .clip(CircleShape)
                           )
                           Spacer(modifier = Modifier.padding(end = 10.dp))
                           Text(text = name.value, style = MaterialTheme.typography.titleLarge)
                       }
                   }
                   LazyVerticalGrid(
                       columns = GridCells.Adaptive(180.dp),
                       state = LazyGridState(),
                       contentPadding = PaddingValues(12.dp),
                       horizontalArrangement = Arrangement.spacedBy(12.dp),
                       verticalArrangement = Arrangement.spacedBy(12.dp),
                       modifier = Modifier
                           .fillMaxWidth()
                           .weight(3.8f)
                   ){
                       items(response.data){ data ->
                           val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                           ProductItemCard(
                               name = data.name ,
                               image = data.image,
                               brand = data.brand,
                                   itemSize = itemSize,
                               onClick = {
                                   navController.navigate(Screen.ProductDetail.createRoute(data.id))
                               } )
                       }
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