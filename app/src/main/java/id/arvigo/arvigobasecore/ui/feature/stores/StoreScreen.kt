package id.arvigo.arvigobasecore.ui.feature.stores

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.data.source.network.response.stores.StoreData
import id.arvigo.arvigobasecore.data.source.network.response.stores.StoreDataItem
import id.arvigo.arvigobasecore.ui.feature.stores.uistate.StoreUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun StoreScreen(
    navController: NavController,
) {
    StoreScreenContent(
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreenContent(
    navController: NavController,
) {

    val viewModel: StoreViewModel = getViewModel()

    androidx.compose.material3.Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Toko", style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
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

        when (response) {
            is StoreUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is StoreUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .padding(it)
                ) {
                    items(response.data) { item ->
                        val data = item.storeDataItems
                        StoreCard(
                            data = item,
                            onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "stores",
                                    value = data
                                )
                                navController.navigate(Screen.StoreDetail.route)
                            }
                        )
                    }
                }
            }

            is StoreUiState.Failure -> {
                Text(text = "Error")
            }

            else -> {
                Text(text = "Empty")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreCard(
    data: StoreData,
    onClick: () -> Unit,
) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.img_face_sample),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(43.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp),
                    ) {
                        Text(
                            text = data.merchantName,
                            style = MaterialTheme.typography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.width(150.dp)
                        )
                        Text(text = data.location, style = MaterialTheme.typography.bodySmall)
                    }
                }
                Button(
                    onClick = {
                        onClick()
                    },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = "Lihat",
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.SemiBold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                contentPadding = PaddingValues(start = 2.dp, end = 2.dp),
            ) {
                if (data.storeDataItems.size >= 3) {
                    items(data.storeDataItems.subList(0, 3)) { data ->
                        StoreProductItemCard(
                            data = data
                        )
                    }
                } else {
                    items(data.storeDataItems) { data ->
                        StoreProductItemCard(
                            data = data
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StoreProductItemCard(
    data: StoreDataItem
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(85.dp)
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxSize()
                .clickable { }
        ) {
            Column() {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Rp ${data.price}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun StorePrev() {

}