package id.arvigo.arvigobasecore.ui.feature.profile.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import id.arvigo.arvigobasecore.ui.component.StatelessTopBar
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PricingScreen() {
    Scaffold(
        topBar = { PricingTopBar(onMenuClick = {}) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                CardPricePremium()
                Spacer(modifier = Modifier.padding(5.dp))
                CardPriceFree()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPricePremium() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 10.dp)
        ) {
            Text(text = "Premium")
            Text(text = "Rp 20.000 per user/month")
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, 10.dp),
            ) {
                Text(text = "✅ Dapatkan benefit 1")
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "✅ Dapatkan benefit 2")
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "✅ Dapatkan benefit 3")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Beli Sekarang")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPriceFree() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 10.dp)
        ) {
            Text(text = "Free")
            Text(text = "Rp 0 per user/month")
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, 10.dp),
            ) {
                Text(text = "✅ Dapatkan benefit 1")
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "✅ Dapatkan benefit 2")
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "✅ Dapatkan benefit 3")
            }
        }
    }
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
    ArvigoBaseCoreTheme {
        PricingScreen()
    }
}