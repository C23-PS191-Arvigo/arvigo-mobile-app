package id.arvigo.arvigobasecore.ui.feature.subscription

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
        columnOne = {
            Text(text = "Premium")
            Text(text = "Rp 20.000 per user/month")
        },
        columnTwo = {
            Text(text = "✅ Dapatkan benefit 1")
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "✅ Dapatkan benefit 2")
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "✅ Dapatkan benefit 3")
        },
        button = {
            Button(onClick = { navController.navigate(Screen.Payment.route) }) {
                Text(text = "Beli Sekarang")
            }
        }
    )
}

@Composable
fun CardPriceFree() {
    CustomCardTwo(
        columnOne = {
            Text(text = "Free")
            Text(text = "Rp 0 per user/month")
        },
        columnTwo = {
            Text(text = "✅ Dapatkan benefit 1")
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "✅ Dapatkan benefit 2")
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "✅ Dapatkan benefit 3")
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