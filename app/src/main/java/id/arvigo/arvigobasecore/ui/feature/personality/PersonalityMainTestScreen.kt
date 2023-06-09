package id.arvigo.arvigobasecore.ui.feature.personality

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import id.arvigo.arvigobasecore.data.source.network.request.QuestionnaireRequest
import id.arvigo.arvigobasecore.data.source.network.request.QuestionnaireRequestX
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
import id.arvigo.arvigobasecore.domain.model.QuesResult
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import id.arvigo.arvigobasecore.ui.feature.personality.uistate.PersonalityUiState
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun PersonalityMainTestScreen(
   navController: NavController,
) {

    PersonalityMainTestContent(
        navController = navController,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalityMainTestContent(
    navController: NavController,
) {
    val viewModel: PersonalityViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    val questionnaireRequest = remember { QuestionnaireRequestX() }
    val switchButton = remember { mutableStateOf(false) }
    val personalResult = remember { mutableStateOf("") }

    var checkValueOption = remember {
        mutableStateOf(questionnaireRequest.oPN10)
    }


    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Personality Question")
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
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            val result = viewModel.response.value
            when(result) {
                is PersonalityUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3.8f)
                    ) {
                        items(result.data) {response ->
                            PersonalTestCard(data = response, questionnaireRequest = questionnaireRequest)
                        }
                    }
                }
                is PersonalityUiState.Failure -> {
                    Text(text = result.error.message ?: "Unknown Error")
                }
                is PersonalityUiState.SuccessQuestion -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(3.8f)
                            .padding(horizontal = 16.dp)
                    ) {
                        item {
                            QuestionResultCardItem(title = "Extraversion", result = result.data.percentageOfExtraversion.toString(), content = "Extraversion adalah tipe kepribadian yang paling mudah bergaul dan seseorang yang aktif, suka bersenang-senang dan penuh semangat dan humor. Pencetak skor rendah adalah individu yang lebih menyendiri, yang lebih suka menghabiskan waktu sendirian atau dengan sekelompok kecil teman dekat." )
                            QuestionResultCardItem(title = "Neuroticism", result = result.data.percentageOfNeurotic.toString(), content = "Neuroticism adalah indikasi individu emosional yang merasa sangat dalam dan memiliki kecenderungan untuk khawatir dan sadar diri. Skor rendah cenderung lebih tahan terhadap perubahan dan tetap tenang di bawah tekanan." )
                            QuestionResultCardItem(title = "Agreeableness", result = result.data.percentageOfAgreeable.toString() , content = "Keramahan berbicara kepada individu yang baik hati dan simpatik yang rukun dengan orang lain. Pencetak skor tinggi adalah individu yang sangat simpatik yang baik hati dan berhubungan baik dengan orang lain.")
                            QuestionResultCardItem(title = "Conscientiousness", result = result.data.percentageOfConscientious.toString(), content = "Conscientiousness berbicara tentang tipe kepribadian yang andal dan pekerja keras yang menerapkan disiplin diri dan pengendalian diri untuk mencapai tujuan mereka. Pencetak skor tinggi umumnya adalah pembuat keputusan yang cermat yang mempertimbangkan semua fakta dengan hati-hati.")
                            QuestionResultCardItem(title = "Opennes to Experience", result = result.data.percentageOfOpeness.toString(), content = "Keterbukaan terhadap Pengalaman mewakili pemikiran imajinatif dan kreatif yang tetap ingin tahu tentang dunia di sekitar mereka. Pencetak skor rendah lebih konvensional dalam pandangan mereka terhadap hal-hal baru dan lebih menyukai rutinitas yang akrab.")
                        }
                    }

                    val properties = mapOf(
                        "Extraversion" to result.data.percentageOfExtraversion,
                        "Neurotic" to result.data.percentageOfNeurotic,
                        "Agreeable" to result.data.percentageOfAgreeable,
                        "Conscientious" to result.data.percentageOfConscientious,
                        "Openness" to result.data.percentageOfOpeness
                    )

                    val highestPercentage = properties.maxByOrNull { it.value }

                    val highestPropertyName = highestPercentage?.key
                    val highestPercentageValue = highestPercentage?.value

                    Log.d("Higest Value of Personaltiy", "PersonalityMainTestScreen: $highestPropertyName")
                    Log.d("Higest Value of Personaltiy", "PersonalityMainTestScreen: $highestPercentageValue")

                    personalResult.value = highestPropertyName.toString()
                    switchButton.value = true
                }
                PersonalityUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                PersonalityUiState.Empty -> {
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
               if (switchButton.value) {
                   PrimaryButton(title = "Lihat Rekomendasi", onClick = {
                        navController.navigate(Screen.PersonalRecomendation.passData(personalResult.value))
                   })
               } else {
                   PrimaryButton(title = "Submit", onClick = {
                       Log.d("PersonalityMainTestScreen", "PersonalityMainTestScreen: ${questionnaireRequest.eXT1}")
                       viewModel.submitQuestionnaire(questionnaireRequest)
                   })
               }


            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalTestCard(
    data: PersonalityData,
    questionnaireRequest: QuestionnaireRequestX,
) {

    var state by remember {
        mutableStateOf(false)
    }

    val radioOptions = listOf(1, 2, 3, 4, 5)
    //val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2] ) }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(data.selectedOption) }




    Surface() {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = data.question, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Strongly\nDisagree", style = MaterialTheme.typography.titleMedium)
                radioOptions.forEach { text ->
                    Box(
                        Modifier
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    data.selectedOption = text
                                    updateQuestionnaireRequest(data, questionnaireRequest)
                                    onOptionSelected(text)
                                    Log.d("PersonalTestCard", "PersonalTestCard: ${questionnaireRequest.eXT1}")
                                    Log.d("PersonalTestCard", "PersonalTestCard: ${data.selectedOption.toString()}")
                                }
                            )
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                data.selectedOption = text
                                updateQuestionnaireRequest(data, questionnaireRequest)
                                Log.d("PersonalTestCard", "PersonalTestCard: ${questionnaireRequest.eXT1}")
                                Log.d("PersonalTestCard", "PersonalTestCard: ${data.selectedOption.toString()}")
                            }
                        )
                    }
                }
                Text(text = "Strongly\nAgree", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Divider()
        }
    }
}


@Composable
fun QuestionResultCardItem(
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
                    Spacer(modifier = Modifier.padding(start = 2.dp))
                    Text(
                        text = result,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 12.dp),
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

private fun updateQuestionnaireRequest(data: PersonalityData, questionnaireRequest: QuestionnaireRequestX) {
    when (data.id) {
        1 -> questionnaireRequest.aGR1 = data.selectedOption
        2 -> questionnaireRequest.aGR2 = data.selectedOption
        3 -> questionnaireRequest.aGR3 = data.selectedOption
        4 -> questionnaireRequest.aGR4 = data.selectedOption
        5 -> questionnaireRequest.aGR5 = data.selectedOption
        6 -> questionnaireRequest.aGR6 = data.selectedOption
        7 -> questionnaireRequest.aGR7 = data.selectedOption
        8 -> questionnaireRequest.aGR8 = data.selectedOption
        9 -> questionnaireRequest.aGR9 = data.selectedOption
        10 -> questionnaireRequest.aGR10 = data.selectedOption
        11 -> questionnaireRequest.cSN1 = data.selectedOption
        12 -> questionnaireRequest.cSN2 = data.selectedOption
        13 -> questionnaireRequest.cSN3 = data.selectedOption
        14 -> questionnaireRequest.cSN4 = data.selectedOption
        15 -> questionnaireRequest.cSN5 = data.selectedOption
        16 -> questionnaireRequest.cSN6 = data.selectedOption
        17 -> questionnaireRequest.cSN7 = data.selectedOption
        18 -> questionnaireRequest.cSN8 = data.selectedOption
        19 -> questionnaireRequest.cSN9 = data.selectedOption
        20 -> questionnaireRequest.cSN10 = data.selectedOption
        21 -> questionnaireRequest.eST1 = data.selectedOption
        22 -> questionnaireRequest.eST2 = data.selectedOption
        23 -> questionnaireRequest.eST3 = data.selectedOption
        24 -> questionnaireRequest.eST4 = data.selectedOption
        25 -> questionnaireRequest.eST5 = data.selectedOption
        26 -> questionnaireRequest.eST6 = data.selectedOption
        27 -> questionnaireRequest.eST7 = data.selectedOption
        28 -> questionnaireRequest.eST8 = data.selectedOption
        29 -> questionnaireRequest.eST9 = data.selectedOption
        30 -> questionnaireRequest.eST10 = data.selectedOption
        31 -> questionnaireRequest.eXT1 = data.selectedOption
        32 -> questionnaireRequest.eXT2 = data.selectedOption
        33 -> questionnaireRequest.eXT3 = data.selectedOption
        34 -> questionnaireRequest.eXT4 = data.selectedOption
        35 -> questionnaireRequest.eXT5 = data.selectedOption
        36 -> questionnaireRequest.eXT6 = data.selectedOption
        37 -> questionnaireRequest.eXT7 = data.selectedOption
        38 -> questionnaireRequest.eXT8 = data.selectedOption
        39 -> questionnaireRequest.eXT9 = data.selectedOption
        40 -> questionnaireRequest.eXT10 = data.selectedOption
        41 -> questionnaireRequest.oPN1 = data.selectedOption
        42 -> questionnaireRequest.oPN2 = data.selectedOption
        43 -> questionnaireRequest.oPN3 = data.selectedOption
        44 -> questionnaireRequest.oPN4 = data.selectedOption
        45 -> questionnaireRequest.oPN5 = data.selectedOption
        46 -> questionnaireRequest.oPN6 = data.selectedOption
        47 -> questionnaireRequest.oPN7 = data.selectedOption
        48 -> questionnaireRequest.oPN8 = data.selectedOption
        49 -> questionnaireRequest.oPN9 = data.selectedOption
        50 -> questionnaireRequest.oPN10 = data.selectedOption
        // Add cases for other questions...
        // Match the question string to the corresponding property in the QuestionnaireRequest

    }
}

private fun isQuestionnaireCompleted(questionnaireRequest: QuestionnaireRequestX): Boolean {
    // Check if all properties in the questionnaireRequest are non-null
    return questionnaireRequest.aGR1 != null &&
            questionnaireRequest.aGR2 != null &&
            questionnaireRequest.aGR3 != null &&
            questionnaireRequest.aGR4 != null &&
            questionnaireRequest.aGR5 != null &&
            questionnaireRequest.aGR6 != null &&
            questionnaireRequest.aGR7 != null &&
            questionnaireRequest.aGR8 != null &&
            questionnaireRequest.aGR9 != null &&
            questionnaireRequest.aGR10 != null &&
            questionnaireRequest.cSN1 != null &&
            questionnaireRequest.cSN2 != null &&
            questionnaireRequest.cSN3 != null &&
            questionnaireRequest.cSN4 != null &&
            questionnaireRequest.cSN5 != null &&
            questionnaireRequest.cSN6 != null &&
            questionnaireRequest.cSN7 != null &&
            questionnaireRequest.cSN8 != null &&
            questionnaireRequest.cSN9 != null &&
            questionnaireRequest.cSN10 != null &&
            questionnaireRequest.eST1 != null &&
            questionnaireRequest.eST2 != null &&
            questionnaireRequest.eST3 != null &&
            questionnaireRequest.eST4 != null &&
            questionnaireRequest.eST5 != null &&
            questionnaireRequest.eST6 != null &&
            questionnaireRequest.eST7 != null &&
            questionnaireRequest.eST8 != null &&
            questionnaireRequest.eST9 != null &&
            questionnaireRequest.eST10 != null &&
            questionnaireRequest.eXT1 != null &&
            questionnaireRequest.eXT2 != null &&
            questionnaireRequest.eXT3 != null &&
            questionnaireRequest.eXT4 != null &&
            questionnaireRequest.eXT5 != null &&
            questionnaireRequest.eXT6 != null &&
            questionnaireRequest.eXT7 != null &&
            questionnaireRequest.eXT8 != null &&
            questionnaireRequest.eXT9 != null &&
            questionnaireRequest.eXT10 != null &&
            questionnaireRequest.oPN1 != null &&
            questionnaireRequest.oPN2 != null &&
            questionnaireRequest.oPN3 != null &&
            questionnaireRequest.oPN4 != null &&
            questionnaireRequest.oPN5 != null &&
            questionnaireRequest.oPN6 != null &&
            questionnaireRequest.oPN7 != null &&
            questionnaireRequest.oPN8 != null &&
            questionnaireRequest.oPN9 != null &&
            questionnaireRequest.oPN10 != null
    // Add checks for other properties...

}


@Preview
@Composable
fun PersonalTestCardPrev() {
}