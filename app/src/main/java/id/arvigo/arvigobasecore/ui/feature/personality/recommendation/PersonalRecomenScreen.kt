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

const val EXTRAVERSION = "Extraversion"
const val AGREEABLENESS = "Agreeable"
const val CONSCIENTIOUSNESS = "Conscientious"
const val NEUROTICISM = "Neurotic"
const val OPENNESS = "Openness"

@Composable
fun PersonalRecomenScreen(
    navController: NavController,
    personalResult: String,
) {
    PersonalRecomenContent(
        navController = navController,
        personalResult = personalResult,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalRecomenContent(
    navController: NavController,
    personalResult: String,
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
                    when (personalResult) {
                        EXTRAVERSION -> {
                            Text(text = "Anda adalah seseorang yang ekstrovert. Anda cenderung energik, sosial, dan suka berinteraksi dengan orang lain. Anda menikmati kehidupan sosial, memiliki banyak teman, dan senang berada di tengah perhatian.", style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                            ))
                        }
                        AGREEABLENESS -> {
                            Text(text = "Anda memiliki tingkat kesetujuan yang tinggi. Anda cenderung ramah, kooperatif, dan empatik terhadap perasaan orang lain. Anda mudah bergaul, peduli pada orang lain, dan memiliki kemampuan untuk bekerja sama dengan baik dalam tim.", style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                            ))
                        }
                        CONSCIENTIOUSNESS -> {
                            Text(text = "Anda memiliki tingkat konsientius yang tinggi. Anda cenderung terorganisir, bertanggung jawab, dan memiliki orientasi terhadap pencapaian. Anda memiliki kemauan yang kuat untuk bekerja keras, mengatur waktu dengan baik, dan mencapai tujuan Anda.", style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                            ))
                        }
                        NEUROTICISM -> {
                            Text(text = "Anda memiliki tingkat neorotisme yang rendah. Anda cenderung tenang, stabil, dan tahan terhadap stres. Anda memiliki kemampuan untuk mengatasi tantangan emosional dengan baik dan jarang terpengaruh oleh perasaan negatif.", style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                            ))
                        }
                        OPENNESS -> {
                            Text(text = "Anda memiliki keterbukaan yang tinggi terhadap pengalaman baru. Anda cenderung kreatif, imajinatif, dan ingin mencoba hal-hal baru. Anda memiliki ketertarikan yang kuat terhadap seni, budaya, dan pengetahuan.", style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                            ))
                        }
                    }
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