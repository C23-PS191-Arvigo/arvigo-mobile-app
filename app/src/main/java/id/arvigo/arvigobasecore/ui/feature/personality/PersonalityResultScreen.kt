package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.PersonalityUiState
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.QuestionnaireUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun PersonalityResultScreen(
    navController: NavController,
) {
    PersonalityResultContent(
        navController = navController,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalityResultContent(
    navController: NavController,
) {

    val viewModel: PersonalityViewModel = getViewModel()


    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            val result = viewModel.questionnaireResponse.value

            when(result) {
                is QuestionnaireUiState.SuccessQuestionnaire -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(3.8f)
                    ) {
                        item {
                            QuestionResultCard(title = "Extraversion", result = result.data.percentageOfExtraversion.toString(), content = "Extraversion adalah tipe kepribadian yang paling mudah bergaul dan seseorang yang aktif, suka bersenang-senang dan penuh semangat dan humor. Pencetak skor rendah adalah individu yang lebih menyendiri, yang lebih suka menghabiskan waktu sendirian atau dengan sekelompok kecil teman dekat." )
                            QuestionResultCard(title = "Neuroticism", result = result.data.percentageOfNeurotic.toString(), content = "Neuroticism adalah indikasi individu emosional yang merasa sangat dalam dan memiliki kecenderungan untuk khawatir dan sadar diri. Skor rendah cenderung lebih tahan terhadap perubahan dan tetap tenang di bawah tekanan." )
                            QuestionResultCard(title = "Agreeableness", result = result.data.percentageOfAgreeable.toString() , content = "Keramahan berbicara kepada individu yang baik hati dan simpatik yang rukun dengan orang lain. Pencetak skor tinggi adalah individu yang sangat simpatik yang baik hati dan berhubungan baik dengan orang lain.")
                            QuestionResultCard(title = "Conscientiousness", result = result.data.percentageOfConscientious.toString(), content = "Conscientiousness berbicara tentang tipe kepribadian yang andal dan pekerja keras yang menerapkan disiplin diri dan pengendalian diri untuk mencapai tujuan mereka. Pencetak skor tinggi umumnya adalah pembuat keputusan yang cermat yang mempertimbangkan semua fakta dengan hati-hati.")
                            QuestionResultCard(title = "Opennes to Experience", result = result.data.percentageOfOpeness.toString(), content = "Keterbukaan terhadap Pengalaman mewakili pemikiran imajinatif dan kreatif yang tetap ingin tahu tentang dunia di sekitar mereka. Pencetak skor rendah lebih konvensional dalam pandangan mereka terhadap hal-hal baru dan lebih menyukai rutinitas yang akrab.")
                        }
                    }
                }
                is QuestionnaireUiState.Failure -> {
                    Text(text = result.error.message ?: "Unknown Error")
                }
                QuestionnaireUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                QuestionnaireUiState.Empty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3.8f)
                    ) {
                        Text(text = "Empty", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(horizontal = 15.dp, vertical = 4.dp)
            ) {
                PrimaryButton(title = "Lihat Rekomendasi", onClick = {

                } )
            }
        }
    }
}

@Composable
fun QuestionResultCard(
    title: String,
    result: String,
    content: String
) {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
               Row() {
                   Text(
                       text = title,
                       style = MaterialTheme.typography.headlineSmall,
                       fontWeight = FontWeight.Bold,
                       modifier = Modifier
                           .padding(12.dp)
                   )
                   Spacer(modifier = Modifier.padding(end = 8.dp))
                   Text(
                       text = result,
                       style = MaterialTheme.typography.headlineSmall,
                       fontWeight = FontWeight.Bold,
                       modifier = Modifier
                           .padding(12.dp),
                       color = MaterialTheme.colorScheme.primary,
                   )
               }
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }
    }
}