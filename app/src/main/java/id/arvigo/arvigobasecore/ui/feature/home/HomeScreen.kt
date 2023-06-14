package id.arvigo.arvigobasecore.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.CarouselCard
import id.arvigo.arvigobasecore.ui.component.alert.PrimaryAlert
import id.arvigo.arvigobasecore.ui.component.cards.CustomCard
import id.arvigo.arvigobasecore.ui.component.cards.ItemListOne
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeFaceState
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomePersonalState
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeScreen(
    navController: NavController,
) {
   HomeContent(
      navController = navController,
   )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeContent(
   navController: NavController,
) {
    val viewModel: HomeViewModel = getViewModel()
    var text by remember { mutableStateOf("") }
    var openDialog = remember { mutableStateOf(false) }
    var url by remember { mutableStateOf("") }
    val ctx = LocalContext.current
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                navController.navigate(Screen.Search.route)
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .padding(horizontal = 12.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "" )
                            Text(text = "Cari", modifier = Modifier.padding(start = 8.dp), fontSize = 16.sp)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Notification.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                CarouselCard()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MainMenu(title = "Toko", icon = R.drawable.ic_stores, onClick = {
                        navController.navigate(Screen.Store.route)
                    })
                    MainMenu(title = "Merek", icon = R.drawable.ic_brand, onClick = {
                        navController.navigate(Screen.Brand.route)
                    })
                    MainMenu(title = "Kacamata", icon = R.drawable.ic_eyewear, onClick = {
                        navController.navigate(Screen.Eyewear.route)
                    })
                    MainMenu(title = "Makeup", icon = R.drawable.ic_makeup, onClick = {
                        navController.navigate(Screen.Makeup.route)
                    })
                }
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MainMenu(title = "Topi", icon = R.drawable.ic_headwear, onClick = {
                        openDialog.value = true
                        url = "https://www.google.com"
                    })
                    MainMenu(title = "Arloji", icon = R.drawable.ic_watch, onClick = {
                        openDialog.value = true
                        url = "https://www.twitter.com"
                    })
                    MainMenu(title = "Sepatu", icon = R.drawable.ic_shoes, onClick = {
                        openDialog.value = true
                        url = "https://www.google.com"
                    })
                    MainMenu(title = "Ransel", icon = R.drawable.ic_bags, onClick = {
                        openDialog.value = true
                        url = "https://www.google.com"
                    })
                }
                if(openDialog.value) {
                    PrimaryAlert(openDialog = openDialog, ctx = ctx, url = url)
                }
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(
                    text = "Rekomendasi untuk kamu",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                CustomCard(
                    desc = "Personalitas",
                    button = "Ambil",
                    onClick = {
                        navController.navigate(Screen.Personality.route)
                    }
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                CustomCard(
                    desc = "Bentuk Wajah",
                    button = "Ambil",
                    onClick = {
                        navController.navigate(Screen.FaceShapeIntro.route)
                    },
                    title = {}
                )
            }

            val responsePersonal = viewModel.responsePersonal.value
            if (responsePersonal is HomePersonalState.Success) {
                if (responsePersonal.data != null) {
                    item {
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        Text(
                                text = "Berdasarkan personalitas kamu",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                                modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                    item {
                        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                        com.google.accompanist.flowlayout.FlowRow(
                                mainAxisSize = SizeMode.Expand,
                                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                        ) {

                            when (responsePersonal) {
                                is HomePersonalState.Success -> {
                                    responsePersonal.data?.take(2)?.forEachIndexed { index, recommendation ->
                                        Box(
                                                modifier = Modifier
                                                        .width(itemSize),
                                                contentAlignment = Alignment.Center
                                        ) {
                                            Card(
                                                    modifier = Modifier
                                                        .padding(
                                                            horizontal = 8.dp,
                                                            vertical = 10.dp
                                                        )
                                                        .fillMaxSize()
                                                        .clickable {
                                                            navController.navigate(
                                                                Screen.ProductDetail.createRoute(
                                                                    recommendation.id
                                                                )
                                                            )
                                                        },
                                                    elevation = CardDefaults.cardElevation(
                                                            defaultElevation = 2.dp
                                                    )
                                            ) {
                                                Column() {
                                                    Card(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(170.dp)
                                                                .padding(
                                                                    horizontal = 8.dp,
                                                                    vertical = 8.dp
                                                                ),
                                                    ) {
                                                        AsyncImage(
                                                                model = ImageRequest.Builder(LocalContext.current)
                                                                        .data(recommendation.image)
                                                                        .crossfade(true)
                                                                        .build(),
                                                                contentDescription = null,
                                                                contentScale = ContentScale.Crop,
                                                                placeholder = painterResource(id = R.drawable.img_placeholder),
                                                                modifier = Modifier
                                                                        .fillMaxWidth()
                                                        )
                                                    }
                                                    Text(
                                                            text = recommendation.name,
                                                            style = MaterialTheme.typography.titleLarge,
                                                            maxLines = 2,
                                                            overflow = TextOverflow.Ellipsis,
                                                            modifier = Modifier.padding(horizontal = 8.dp)
                                                    )
                                                    Spacer(modifier = Modifier.padding(top = 8.dp))
                                                    Text(text = recommendation.brand, style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 8.dp))
                                                    Spacer(modifier = Modifier.padding(top = 12.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                                is HomePersonalState.Failure -> {
                                    Text(text = responsePersonal.error.message ?: "Unknown Error")
                                }
                                HomePersonalState.Loading -> {
                                    CircularProgressIndicator(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .wrapContentSize(align = Alignment.Center)
                                    )
                                }
                                HomePersonalState.Empty -> {
                                    Text(text = "Empty Data")
                                }
                            }
                        }
                    }
                }
            }

            val responseFace = viewModel.responseFace.value
            if (responseFace is HomeFaceState.Success) {
                if (responseFace.data != null) {
                    item {
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        Text(
                                text = "Berdasarkan Bentuk Wajah",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                                modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                    item {
                        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                        com.google.accompanist.flowlayout.FlowRow(
                                mainAxisSize = SizeMode.Expand,
                                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                        ) {


                            when (responseFace) {
                                is HomeFaceState.Success -> {
                                    responseFace.data?.take(2)?.forEachIndexed { index, recommendation ->
                                        Box(
                                                modifier = Modifier
                                                        .width(itemSize),
                                                contentAlignment = Alignment.Center
                                        ) {
                                            Card(
                                                    modifier = Modifier
                                                        .padding(
                                                            horizontal = 8.dp,
                                                            vertical = 10.dp
                                                        )
                                                        .fillMaxSize()
                                                        .clickable {
                                                            navController.navigate(
                                                                Screen.ProductDetail.createRoute(
                                                                    recommendation.id
                                                                )
                                                            )
                                                        },
                                                    elevation = CardDefaults.cardElevation(
                                                            defaultElevation = 2.dp
                                                    )
                                            ) {
                                                Column() {
                                                    Card(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(170.dp)
                                                                .padding(
                                                                    horizontal = 8.dp,
                                                                    vertical = 8.dp
                                                                ),
                                                    ) {
                                                        AsyncImage(
                                                                model = ImageRequest.Builder(LocalContext.current)
                                                                        .data(recommendation.image)
                                                                        .crossfade(true)
                                                                        .build(),
                                                                contentDescription = null,
                                                                contentScale = ContentScale.Crop,
                                                                placeholder = painterResource(id = R.drawable.img_placeholder),
                                                                modifier = Modifier
                                                                        .fillMaxWidth()
                                                        )
                                                    }
                                                    Text(
                                                            text = recommendation.name,
                                                            style = MaterialTheme.typography.titleLarge,
                                                            maxLines = 2,
                                                            overflow = TextOverflow.Ellipsis,
                                                            modifier = Modifier.padding(horizontal = 8.dp)
                                                    )
                                                    Spacer(modifier = Modifier.padding(top = 8.dp))
                                                    Text(text = recommendation.brand, style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 8.dp))
                                                    Spacer(modifier = Modifier.padding(top = 12.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                                is HomeFaceState.Failure -> {
                                    Text(text = responseFace.error.message ?: "Unknown Error")
                                }
                                HomeFaceState.Loading -> {
                                    CircularProgressIndicator(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .wrapContentSize(align = Alignment.Center)
                                    )
                                }
                                HomeFaceState.Empty -> {
                                    Text(text = "Empty Data")
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(
                        text = "Rekomendasi lainnya",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                com.google.accompanist.flowlayout.FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                ) {

                    val response = viewModel.response.value

                    when (response) {
                        is HomeUiState.Success -> {
                            response.data.forEachIndexed { index, recommendation ->
                                Box(
                                    modifier = Modifier
                                        .width(itemSize),
                                    contentAlignment = Alignment.Center
                                ) {
                                    ItemListOne(
                                        navController = navController,
                                        id = recommendation.id,
                                        image = recommendation.image,
                                        name = recommendation.name,
                                        brand = recommendation.brand
                                    )
                                }
                            }
                        }
                        is HomeUiState.Failure -> {
                            Text(text = response.error.message ?: "Unknown Error")
                        }
                        HomeUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                        HomeUiState.Empty -> {
                            Text(text = "Empty Data")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(title: String, icon: Int, onClick: () -> Unit) {
    Card(
        modifier =Modifier
            .size(85.dp),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier
                .width(85.dp)
                .height(85.dp)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = icon), contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() },
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Black.copy(alpha = 0.5f))) {
            }
            Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun HomePrev() {

}