package id.arvigo.arvigobasecore.ui.feature.personality

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import id.arvigo.arvigobasecore.ui.navigation.Screen

@Composable
fun PersonalityScreen(
   navController: NavController,
) {
    PersonalityContent(
       navController = navController,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalityContent(
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Petunjuk Tes Kepribadian")
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
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(3.8f)
            ) {
                item {
                    GuideCard( content = "Bacalah setiap pernyataan dengan cermat sebelum memberikan penilaian.")
                    GuideCard(content = "Pertimbangkan pernyataan tersebut dengan jujur dan objektif, sesuai dengan pendapat dan sikap pribadi Anda.")
                    GuideCard( content = "Jangan terlalu lama mempertimbangkan satu pernyataan. Percaya pada insting Anda dan pilih penilaian yang paling sesuai dengan pandangan Anda secara keseluruhan.")
                    GuideCard( content = "Ingatlah bahwa tidak ada jawaban yang benar atau salah dalam tes ini. Semua jawaban Anda akan membantu dalam membentuk gambaran yang lebih lengkap tentang kepribadian Anda.")
                    GuideCard( content = "Jangan merasa terburu-buru, tetapi jangan pula membiarkan diri Anda terlalu lama terjebak dalam satu pernyataan. Cukup pilih jawaban yang paling sesuai dengan intuisi Anda.")
                    GuideCard( content = "Nikmati proses pengisian tes ini dan gunakan kesempatan ini untuk lebih memahami dan mengeksplorasi aspek-aspek kepribadian Anda sendiri.")
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                PrimaryButton(title = "Mulai Tes", onClick = {
                    navController.navigate(Screen.PersonalityMainTest.route)
                } )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideCard(
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
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardPrev() {

}

