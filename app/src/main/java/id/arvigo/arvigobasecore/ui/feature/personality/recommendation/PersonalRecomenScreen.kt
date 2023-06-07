package id.arvigo.arvigobasecore.ui.feature.personality.recommendation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.ProductRecommendationCard
import id.arvigo.arvigobasecore.ui.feature.home.HomeViewModel
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomeUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun PersonalRecomenScreen(
    navController: NavController,
) {
    PersonalRecomenContent(
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalRecomenContent(
    navController: NavController,
) {

    val viewModel: HomeViewModel = getViewModel()

    Scaffold(

        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Rekomendasi Produk")
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
                .fillMaxSize()
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Analisis Kepribadianmu", style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                    ))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Kepribadian kamu menunjukkan bahwa kamu senang berinteraksi dengan orang lain dan penuh energi. Orang dengan tipe kepribadian ekstraversi cenderung antusias, berorientasi pada tindakan, dan sangat sosial. Kamu adalah individu yang ramah dan suka berbicara dan terhubung dengan orang lain.",
                        style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "Rekomendasi Produk", style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                    ))
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            item {
                val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                com.google.accompanist.flowlayout.FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                ) {

                    val response = viewModel.response.value

                    when (response) {
                        is HomeUiState.Success -> {
                            response.data.forEachIndexed { index, recommendation ->
                                ProductRecommendationCard(
                                    data = recommendation,
                                    onClick = {
                                        navController.navigate(Screen.ProductDetail.createRoute(recommendation.id))
                                    }
                                )
                            }
                        }
                        is HomeUiState.Failure -> {
                            Text(text = response.error.message ?: "Unknown Error")
                        }
                        HomeUiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }
                        HomeUiState.Empty -> {
                            Text(text = "Empty Data")
                        }
                    }
                }
            }
        }
    }
}