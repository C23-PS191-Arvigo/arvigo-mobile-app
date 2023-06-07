package id.arvigo.arvigobasecore.ui.feature.faceshape.recommendation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.data.source.network.request.Test
import id.arvigo.arvigobasecore.util.FaceResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceGuideScreen(
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Bentuk Wajah lainnya")
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
                FaceGuideCard(img = R.drawable.oval, title = FaceResult.OVAL)
                FaceGuideCard(img = R.drawable.circle, title = FaceResult.CIRCLE)
                FaceGuideCard(img = R.drawable.heart, title = FaceResult.HEART)
                FaceGuideCard(img = R.drawable.square, title = FaceResult.SQUARE)
                FaceGuideCard(img = R.drawable.triangle, title = FaceResult.TRIANGLE)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceGuideCard(
    img: Int,
    title: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(bottom = 12.dp)
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
                modifier = Modifier
                    .size(170.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun Test() {
    
}