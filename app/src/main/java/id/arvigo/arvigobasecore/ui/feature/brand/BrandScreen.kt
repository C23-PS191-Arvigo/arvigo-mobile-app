package id.arvigo.arvigobasecore.ui.feature.brand

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BrandScreen(
    navController: NavController,
) {
    BrandScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandScreenContent() {
    Scaffold() {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {

        }
     }
}