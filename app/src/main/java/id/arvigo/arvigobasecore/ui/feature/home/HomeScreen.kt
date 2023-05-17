package id.arvigo.arvigobasecore.ui.feature

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                Column() {
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    CarouselCard()
                }
            }
        }
    }
}


@Preview
@Composable
fun HomePrev() {

}