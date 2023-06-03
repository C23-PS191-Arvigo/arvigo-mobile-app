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
        "Selamat datang di Arvigo" to "Temukan Gaya Unikmu dengan Rekomendasi Produk Kacamata, Baju, Makeup, Sepatu, dan Tas Terbaik!",
        "Pilih Baju yang Mencerminkan Dirimu!" to "Temukan Fashion yang Menggambarkan Jiwa dan Gaya Anda dengan Rekomendasi Baju Tren dan Klasik!",
        "Temukan Kacamata yang Memukau!" to "Dapatkan Penampilan yang Memikat dengan Koleksi Kacamata Terkini dan Pilihan Terbaik untuk Setiap Gaya!",
        "Siap untuk Memulai Petualangan Fashion?" to "Temukan Gaya yang Membuatmu Bercahaya dengan Langkah Pertamamu di Arvigo!"

    ).toList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        HorizontalPager(
            count = 4,
            state = pagerState,
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(2.5f)
        ) { index ->
            Pager(title = map[index].first, desc = map[index].second, anim = anim[index])
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White
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
                    activeColor = MaterialTheme.colorScheme.onSecondary
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
            color = MaterialTheme.colorScheme.onSecondary,
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
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}