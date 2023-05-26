package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.arvigo.arvigobasecore.ui.component.PrimaryButton

@Composable
fun PersonalityScreen(
    navigateToStartTest: () -> Unit,
) {
    PersonalityContent(
        navigateToStartTest = navigateToStartTest
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalityContent(
    navigateToStartTest: () -> Unit
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(3.8f)
            ) {
                GuideCard(no = "1", content = "Terdapat 33 jenis pertanyaan yang harus dijawab dengan data yang benar guna mendapatkan hasil yang akurat")
                GuideCard(no = "2", content = "Terdapat 33 jenis pertanyaan yang harus dijawab dengan data yang benar guna mendapatkan hasil yang akurat")
                GuideCard(no = "3", content = "Terdapat 33 jenis pertanyaan yang harus dijawab dengan data yang benar guna mendapatkan hasil yang akurat")
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                PrimaryButton(title = "Start Test", onClick = {
                    navigateToStartTest()
                } )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideCard(
    no: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Green),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = no,
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.White, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardPrev() {
    GuideCard(no = "1", content = "dfas")
}

