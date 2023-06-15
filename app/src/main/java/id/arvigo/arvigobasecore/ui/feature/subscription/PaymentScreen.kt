package id.arvigo.arvigobasecore.ui.feature.subscription

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import id.arvigo.arvigobasecore.ui.feature.subscription.model.SubscriptionRequest
import id.arvigo.arvigobasecore.ui.navigation.Screen
import id.arvigo.arvigobasecore.util.Constant.IMAGE_BCA
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    uniqueCode: Int
) {
    val viewModel: SubscriptionViewModel = get()
    val isLoading = viewModel.isLoading.value // Collect the isLoading state as a Compose state
    val responseMessage by viewModel.responseMessage.collectAsState()
    val context = LocalContext.current // Get the current context for showing toast
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
                    text = "Metode Pembayaran",
                    modifier = Modifier,
                )
                CustomRowOne(image = IMAGE_BCA, textOne = "11000929443")
                CustomDivider()
                CustomRowOne(image = IMAGE_BCA, textOne = "11000929443")
                CustomDivider()
                CustomRowOne(image = IMAGE_BCA, textOne = "11000929443")
                CustomDivider()
                Text(
                    text = "Rangkuman Pembayaran",
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
                    textOne = "Kode Unik",
                    textTwo = uniqueCode.toString(),
                    arrangement = Arrangement.Top
                )
                CustomLineTwo(
                    textOne = "Total Harga",
                    textTwo = "Rp. 212.000",
                    arrangement = Arrangement.Bottom
                )
                Button(
                    onClick = {
                        val request = SubscriptionRequest(
                            price = 20000,
                            uniqueCode = uniqueCode,
                            message = viewModel.responseMessage.toString(),
                            bank = "BCA"
                        )
                        viewModel.subscribe(request)
                        Log.d("neo-tag", responseMessage.toString())
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = "Bayar Sekarang",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
        //Show Toasts
        LaunchedEffect(responseMessage) {
            responseMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.clearResponseMessage()
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Home.route){
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
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