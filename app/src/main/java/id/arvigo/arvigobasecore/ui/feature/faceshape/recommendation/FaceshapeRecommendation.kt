package id.arvigo.arvigobasecore.ui.feature.faceshape.recommendation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun FaceshapeRecommendation(
    navController: NavController,
    result: String,
    resultImage: String,
) {
    PersonalRecomenContent(
        navController = navController,
        result = result,
        resultImage = resultImage,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalRecomenContent(
    navController: NavController,
    result: String,
    resultImage: String,
) {

    val viewModel: HomeViewModel = getViewModel()

    val isSurvei = remember {
        mutableStateOf(true)
    }

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(resultImage)
                                .placeholder(R.drawable.img_placeholder)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.img_placeholder),
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = result,
                        style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    ))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Kepribadian kamu menunjukkan bahwa kamu senang berinteraksi dengan orang lain dan penuh energi. Orang dengan tipe kepribadian ekstraversi cenderung antusias, berorientasi pada tindakan, dan sangat sosial. Kamu adalah individu yang ramah dan suka berbicara dan terhubung dengan orang lain.",
                        style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(20.dp))
                    if (isSurvei.value) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Menurut kamu, apakah bentuk wajah ini sesuai dengan bentuk wajah kamu?",
                                    style = MaterialTheme.typography.titleLarge)
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                ) {
                                    Button(
                                        onClick = {
                                            isSurvei.value = false
                                        },
                                        shape = RoundedCornerShape(10),
                                        modifier = Modifier
                                            .fillMaxWidth(0.4f)
                                            .height(48.dp)
                                    ) {
                                        Text(text = "Ya")
                                    }
                                    Button(
                                        onClick = {
                                            isSurvei.value = false
                                        },
                                        shape = RoundedCornerShape(10),
                                        modifier = Modifier
                                            .fillMaxWidth(0.4f)
                                            .height(48.dp)
                                    ) {
                                        Text(text = "Tidak")
                                    }
                                }

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(text = "Berdasarkan Bentuk Wajah Anda", style = MaterialTheme.typography.headlineSmall.copy(
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
                                ProductRecommendationCard(data = recommendation)
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