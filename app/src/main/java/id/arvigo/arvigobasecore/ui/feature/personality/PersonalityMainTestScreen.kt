package id.arvigo.arvigobasecore.ui.feature.personality

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.data.source.network.request.QuestionnaireRequest
import id.arvigo.arvigobasecore.data.source.network.request.QuestionnaireRequestX
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityData
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityDataItem
import id.arvigo.arvigobasecore.data.source.network.response.personality.PersonalityResponse
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
                PersonalityUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
                PersonalityUiState.Empty -> {
                    Text(text = "Empty Data")
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(horizontal = 15.dp, vertical = 4.dp)
            ) {
                PrimaryButton(title = "Continue", onClick = {
                    Log.d("PersonalityMainTestScreen", "PersonalityMainTestScreen: ${questionnaireRequest.eXT1}")
                    navController.navigate(Screen.PersonalityResult.route)
                } )
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalTestCard(
    data: PersonalityData,
    questionnaireRequest: QuestionnaireRequestX
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


@Preview
@Composable
fun PersonalTestCardPrev() {
}