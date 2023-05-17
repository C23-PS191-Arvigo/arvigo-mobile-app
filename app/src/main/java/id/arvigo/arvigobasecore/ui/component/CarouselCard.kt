package id.arvigo.arvigobasecore.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import id.arvigo.arvigobasecore.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.scope.Scope
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CarouselCard() {
    val pageState = rememberPagerState(2)
    val sliderList = listOf(
        "https://picsum.photos/id/237/500/800",
        "https://picsum.photos/id/233/500/800",
        "https://picsum.photos/id/236/500/800",
        "https://picsum.photos/id/232/500/800",
        "https://picsum.photos/id/240/500/800",
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            count = sliderList.size,
            state = pageState,
            contentPadding = PaddingValues(horizontal = 50.dp),
            modifier = Modifier
                .height(200.dp)
        ) {
            page ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(sliderList[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img_placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        val scope = CoroutineScope(SupervisorJob())
        repeat(sliderList.size) { it ->
            val color = if (pageState.currentPage == it) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.LightGray
            }
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(color)
            ) {}
        }
    }
}

@Preview
@Composable
fun CarouselSlider() {
    CarouselCard()
}