package id.arvigo.arvigobasecore.ui.feature.eyewear

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.ProductItemCard
import id.arvigo.arvigobasecore.ui.feature.brand.BrandCard
import id.arvigo.arvigobasecore.ui.feature.brand.uistate.BrandUiState
import id.arvigo.arvigobasecore.ui.feature.eyewear.uistate.EyewearUiState
import org.koin.androidx.compose.getViewModel

@Composable
fun EyewearScreen(
    navController: NavController,
) {
    EyewearScreenContent(
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EyewearScreenContent(
    navController: NavController,
) {

    val viewModel: EyewearViewModel = getViewModel()

    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Eyewear")
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
            is EyewearUiState.Success -> {
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
                       ProductItemCard( name = data.name , image = data.image, brand = data.brand ) {
                       }
                    }
                }
            }
            is EyewearUiState.Failure -> {
                Text(text = response.error.message ?: "Unknown Error")
            }
            EyewearUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            EyewearUiState.Empty -> {
                Text(text = "Empty Data")
            }
        }
    }
}