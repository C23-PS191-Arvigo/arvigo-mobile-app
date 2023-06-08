package id.arvigo.arvigobasecore.ui.feature.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavController,
) {

    Scaffold(

        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Notifikasi")
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
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {
            item {
                NotificationCard(img = R.drawable.ic_headwear, title = "Topi Outdoor", brand = "Eiger", time = "Kamis, 8 Juni 2023")
                NotificationCard(img = R.drawable.ic_eyewear, title = "Kacamata Bluecromic", brand = "Chanel", time = "Selasa, 6 Juni 2023")
                NotificationCard(img = R.drawable.ic_makeup, title = "Soothing Cleanser", brand = "SK - II", time = "Senin, 5 Juni 2023")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationCard(
    img: Int,
    title: String,
    brand: String,
    time: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(text = "Produk", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = "Produk baru tersedia di Arvigo", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(6.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(170.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = brand, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = title, style = MaterialTheme.typography.titleLarge)
                }
            }
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Text(text = time, style = MaterialTheme.typography.labelSmall.copy(
            color = Color.Gray,
        ))
        Spacer(modifier = Modifier.padding(8.dp))
        Divider(
            color = Color.Gray,
        )
    }
}