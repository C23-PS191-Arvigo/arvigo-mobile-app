package id.arvigo.arvigobasecore.ui.feature.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavDestinationDsl
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.CarouselCard
import id.arvigo.arvigobasecore.ui.component.PrimarySearch
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeUiState
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.PersonalityUiState
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
                            Text(text = "Search", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
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
                    MainMenu(title = "Stores", icon = R.drawable.ic_stores, onClick = {
                        navController.navigate(Screen.Store.route)
                    })
                    MainMenu(title = "Brands", icon = R.drawable.ic_brand, onClick = {
                        navController.navigate(Screen.Brand.route)
                    })
                    MainMenu(title = "Eyewear", icon = R.drawable.ic_eyewear, onClick = {
                        navController.navigate(Screen.Eyewear.route)
                    })
                    MainMenu(title = "Makeup", icon = R.drawable.ic_makeup, onClick = {})
                }
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MainMenu(title = "Headwear", icon = R.drawable.ic_headwear, onClick = {})
                    MainMenu(title = "Watch", icon = R.drawable.ic_watch, onClick = {})
                    MainMenu(title = "Shoes", icon = R.drawable.ic_shoes, onClick = {})
                    MainMenu(title = "Bags", icon = R.drawable.ic_bags, onClick = {})
                }
                Spacer(modifier = Modifier.padding(top = 26.dp))
                Text(
                    text = "Recommendation for you",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        ) {
                            Text(text = "Personality", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                            Button(onClick = {
                                navController.navigate(Screen.Personality.route)
                            }) {
                                Text(text = "Test", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        ) {
                            Text(text = "Face shape", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                            Button(onClick = {
                                navController.navigate(Screen.FaceShapeIntro.route)
                            }) {
                                Text(text = "Test", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 26.dp))
                Text(
                    text = "Other Recommendation",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
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
                                        .width(itemSize)
                                        .height(300.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp, vertical = 10.dp)
                                            .height(300.dp)
                                            .fillMaxSize()
                                            .clickable { }
                                    ) {
                                        Column() {
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(160.dp)
                                                    .padding(horizontal = 10.dp, vertical = 10.dp),
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
                                                style = MaterialTheme.typography.headlineSmall,
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.padding(horizontal = 10.dp)
                                            )
                                            Spacer(modifier = Modifier.padding(top = 10.dp))
                                            Text(text = recommendation.brand, style = MaterialTheme.typography.titleLarge.copy(color = Color.Gray), modifier = Modifier.padding(horizontal = 10.dp))
                                        }
                                    }
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

fun LazyGridScope.GridContent() {
    GridCells.Fixed(2)
    items(10) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Item $it", style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun HomePrev() {

}