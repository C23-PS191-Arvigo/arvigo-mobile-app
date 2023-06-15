package id.arvigo.arvigobasecore.ui.feature.subscription

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.StatelessTopBar
import id.arvigo.arvigobasecore.ui.component.cards.CustomCardTwo
import id.arvigo.arvigobasecore.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PricingScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { PricingTopBar(onMenuClick = { navController.popBackStack() }) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                CardPricePremium(navController)
                Spacer(modifier = Modifier.padding(5.dp))
                CardPriceFree()
            }
        }
    }
}

@Composable
fun CardPricePremium(
    navController: NavController
) {
    CustomCardTwo(
        columnOneText = "Premium",
        columnOneTextTwo = "Rp. 20.000 / Bulan",
        columnTwo = {
            Row {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(text = "Rekomendasi Produk Teratas")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(text = "Coba produk dengan menggunakan AR tanpa ada batasan pemakaian!")
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(text = "Customer Service prioritas")
            }
        },
        button = {
            val uniqueCode = (100 until 1000).random()
            Button(
                onClick = {
                    navController.navigate(
                        route = Screen.Payment.createRoute(uniqueCode = uniqueCode)
                    )
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Beli Sekarang")
            }
        }
    )
}

@Composable
fun CardPriceFree() {
    CustomCardTwo(
        columnOneText = "Free",
        columnOneTextTwo = "Rp. 0 / Bulan",
        columnTwo = {
            Row {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(text = "Fitur basic dari Arvigo for Consumer")
            }
            Row {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(text = "Coba produk dengan menggunakan AR")
            }
        },
        button = {}
    )
}

@Composable
fun PricingTopBar(
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
fun PricingPreview() {
    /*    ArvigoBaseCoreTheme {
            PricingScreen()
        }*/
}