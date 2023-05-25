package id.arvigo.arvigobasecore.ui.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.MenuRowItem
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = { DefTopBar(onMenuClick = {}) }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            ProfileCard()
            Spacer(modifier = Modifier.padding(20.dp))
            SubscriptionCard()
            PersonalityCard()
            FaceTypeCard()
            ProfileRowItems()
        }
    }
}

@Composable
fun DefTopBar(onMenuClick: () -> Unit) {
    SmallTopAppBar(
        title = { Text(text = "Profile") },
        actions = {
            IconButton(onClick = { onMenuClick }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "",
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .wrapContentWidth(Alignment.Start),
    ) {
        Card(
            modifier = Modifier
                .width(80.dp)
                .padding(horizontal = 4.dp, vertical = 4.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://media.wired.com/photos/5ca648a330f00e47fd82ae77/master/w_2560%2Cc_limit/Culture_Matrix_Code_corridor.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.img_placeholder),
                alignment = Alignment.Center,
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Name",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp),
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = "email@example.com",
                style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionCard() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                 modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Subscription",
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Text(
                    text = "You have not subscribed.",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Pricing")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalityCard() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                 modifier = Modifier
                     .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Subscription",
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Text(
                    text = "Personality",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
                 //   .align(Alignment.End)
            ) {
                Text(text = "See")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceTypeCard() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Face Type",
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.padding(top = 5.dp))
                Text(
                    text = "Rounded",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Change")
            }
        }
    }
}

@Composable
fun ProfileRowItems() {
    MenuRowItem(name = "About App") {}
    MenuRowItem(name = "Logout") {}
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ArvigoBaseCoreTheme {
        ProfileScreen()
    }
}