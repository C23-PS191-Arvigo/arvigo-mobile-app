package id.arvigo.arvigobasecore.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.data.source.network.response.brands.Brand
import id.arvigo.arvigobasecore.data.source.network.response.home_product.Recommendation

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProductRecommendationCard(
    data: Recommendation,
    onClick: () -> Unit,
) {
    val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
    Box(
        modifier = Modifier
            .width(itemSize)
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .fillMaxSize()
                .height(300.dp)
                .clickable {
                    onClick()
                }
        ) {
            Column() {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Spacer(modifier = Modifier.padding(top = 2.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Chip(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        colors = ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )) {
                        Text(text = data.tags[0], style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                        ))
                    }
                    Text(
                        text = data.brand,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color.Gray,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductPrev() {
}