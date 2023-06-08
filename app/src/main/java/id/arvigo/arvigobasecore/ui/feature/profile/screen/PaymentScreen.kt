package id.arvigo.arvigobasecore.ui.feature.profile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.StatelessTopBar
import id.arvigo.arvigobasecore.util.Constant.IMAGE_BCA

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { PaymentTopBar(onMenuClick = { navController.popBackStack() }) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Payment Method",
                    modifier = Modifier,
                )
                CustomRowOne(image = IMAGE_BCA, textOne = "11000929443")
                CustomDivider()
                CustomRowOne(image = IMAGE_BCA, textOne = "11000929443")
                CustomDivider()
                CustomRowOne(image = IMAGE_BCA, textOne = "11000929443")
                CustomDivider()
                Text(
                    text = "Ordering",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )
                CustomLineTwo(
                    textOne = "Premium",
                    textTwo = "Rp. 20.000",
                    arrangement = Arrangement.Top
                )
                CustomLineTwo(
                    textOne = "Unique Code",
                    textTwo = "2423908",
                    arrangement = Arrangement.Top
                )
                CustomLineTwo(textOne = "Total Price", textTwo = "Rp. 212.000", arrangement = Arrangement.Bottom)
            }
        }
    }
}

@Composable
fun CustomLineTwo(
   textOne: String,
   textTwo: String,
   arrangement: Arrangement.Vertical
) {
    Column(
        verticalArrangement = arrangement
    ) {
        Text(
            text = textOne,
            style = MaterialTheme.typography.titleSmall.copy(color = Color.Gray),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Text(
            text = textTwo,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp),
        )
        CustomDivider()
    }

}

@Composable
fun CustomDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 5.dp),
        color = Color.Gray
    )
}

@Composable
fun CustomRowOne(
    image: String,
    textOne: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .wrapContentWidth(Alignment.Start),
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .padding(horizontal = 4.dp, vertical = 4.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.img_placeholder),
                alignment = Alignment.Center,
                modifier = Modifier.clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = textOne,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp),
            )
        }
    }
}

@Composable
fun PaymentTopBar(
    onMenuClick: () -> Unit,
) {
    StatelessTopBar(
        navigationIcon = {
            IconButton(onClick =
            { onMenuClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        title = "Pembayaran",
        actionIcon = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PaymentPreview() {
    /*    ArvigoBaseCoreTheme {
            PricingScreen()
        }*/
}