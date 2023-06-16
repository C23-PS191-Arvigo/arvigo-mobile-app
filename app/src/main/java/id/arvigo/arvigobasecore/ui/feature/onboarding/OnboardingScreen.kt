package id.arvigo.arvigobasecore.ui.feature.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.AppText
import id.arvigo.arvigobasecore.ui.component.LottieAnim
import id.arvigo.arvigobasecore.ui.feature.splash.SplashViewModel
import id.arvigo.arvigobasecore.ui.navigation.Screen
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
) {
    val viewModel: SplashViewModel = getViewModel()
    val pagerState = rememberPagerState(0)
    val anim = listOf(
        R.raw.welcome,
        R.raw.shop,
        R.raw.eyewear,
        R.raw.ecommerce,
    )
    val map = mapOf(
        "Dapatkan Rekomendasi Fesyen Terbaik" to "Dapatkan rekomendasi produk fesyen terbaik buat kamu berdasarkan bentuk wajah, kepribadian, dan melalui pertanyaan",
        "Mencoba Produk Fesyen Hanya Melalui Aplikasi" to "Bingung memilih produk fesyen di e-commerce karena tidak bisa mencobanya? Tenang, kamu bisa mencobanya di Arvigo",
        "Depatkan Penawaran Produk Fesyen Terbaik" to "Belanja makin hemat dengan mendapatkan penawaran produk fesyen terbaik dari berbagai toko online & offline di seluruh Indonesia",
        "Semuanya Ada Dalam Satu Aplikasi di Arvigo" to "Jadikan dirimu #MakinPercayaDiri dengan memulai langkah pertamamu di Arvigo"

    ).toList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        HorizontalPager(
            count = 4,
            state = pagerState,
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(5f)
        ) { index ->
            Pager(title = map[index].first, desc = map[index].second, anim = anim[index])
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(50.dp),
            contentAlignment = Center
        ) {
            this@Column.AnimatedVisibility(
                visible = pagerState.currentPage == 3,
                exit = fadeOut(),
                enter = fadeIn()
            ) {
                ElevatedButton(
                    onClick = {
                         viewModel.saveOnboardingStatus(true)
                         navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.size(height = 55.dp, width = 280.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                ) {
                    AppText(
                        text = "Mulai Sekarang",
                        size = 20,
                        color = Color.White,
                    )
                }
            }
            if (pagerState.currentPage != 3) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun Pager(
    title: String,
    desc: String,
    anim: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnim(anim = anim, modifier = Modifier.height(300.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = title,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = desc,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
    }
}