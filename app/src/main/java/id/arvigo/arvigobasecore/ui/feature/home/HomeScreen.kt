package id.arvigo.arvigobasecore.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.CarouselCard
import id.arvigo.arvigobasecore.ui.component.PrimarySearch

@Composable
fun HomeScreen() {
   HomeContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent() {
    val text by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    PrimarySearch(query = text)
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
            modifier = Modifier.padding(it)
        ) {
            item {
                Column {
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    CarouselCard()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MainMenu(title = "Stores", icon = R.drawable.ic_stores, onClick = {})
                        MainMenu(title = "Brands", icon = R.drawable.ic_brand, onClick = {})
                        MainMenu(title = "Eyewear", icon = R.drawable.ic_eyewear, onClick = {})
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
                    Spacer(modifier = Modifier.padding(top = 22.dp))
                    Text(
                        text = "Other Recommendation",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color.Black, fontWeight = FontWeight.Bold), textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    Spacer(modifier = Modifier.padding(top = 400.dp))
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