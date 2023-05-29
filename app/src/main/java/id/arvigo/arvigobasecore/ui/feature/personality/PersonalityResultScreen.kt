package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PersonalityResultScreen() {
    PersonalityResultContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalityResultContent() {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
        ) {

        }
    }
}