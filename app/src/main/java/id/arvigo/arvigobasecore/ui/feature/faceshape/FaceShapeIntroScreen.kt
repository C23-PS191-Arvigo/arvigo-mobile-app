package id.arvigo.arvigobasecore.ui.feature.faceshape

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import id.arvigo.arvigobasecore.ui.navigation.Screen

@Composable
fun FaceShapeIntroScreen(navController: NavController) {
    FaceShapeIntroContent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceShapeIntroContent(
    navController: NavController,
) {


    Scaffold(
        modifier = Modifier,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Face Shape Guide")
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
                    GuideCard(no = "1", content = "Berikut merupakan contoh pengambilan gambar yang disarankan, perhatikan gambar di atas", showImage = true)
                    GuideCard(no = "2", content = "Pastikan wajah terlihat jelas dan tidak ada objek yang menghalangi seperti rambut dan hal-hal lainnya", showImage = false)
                    GuideCard(no = "3", content = "Tidak diperkenankan menggunakan aksesoris pada wajah seperti masker atau kacamata pada saat pengambilan gambar, hal ini dimaksudkan untuk membuat hasil rekomendasi menjadi lebih akurat.", showImage = false)
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                PrimaryButton(title = "Scan Your Face", onClick = {
                    navController.navigate(Screen.FaceShapePhoto.route)
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
    showImage: Boolean,
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
            Column() {
                if (showImage) {
                    Image(
                        painter = painterResource(id = R.drawable.img_face_sample),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(top = 12.dp)
                    )
                }
                Text(
                    text = content,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }
    }
}