package id.arvigo.arvigobasecore.ui.feature.profile

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.arvigo.arvigobasecore.ui.component.StatelessTopBar
import id.arvigo.arvigobasecore.ui.component.alert.AlertAbout
import id.arvigo.arvigobasecore.ui.component.alert.AlertFeatureUnavailable
import id.arvigo.arvigobasecore.ui.component.alert.AlertLogout
import id.arvigo.arvigobasecore.ui.component.cards.CustomCard
import id.arvigo.arvigobasecore.ui.component.cards.CustomCardThree
import id.arvigo.arvigobasecore.ui.component.rows.CustomRowTwo
import id.arvigo.arvigobasecore.ui.component.rows.MenuRowItem
import id.arvigo.arvigobasecore.ui.feature.home.HomeViewModel
import id.arvigo.arvigobasecore.ui.feature.home.uistate.HomePersonalState
import id.arvigo.arvigobasecore.ui.feature.personality.PersonalityViewModel
import id.arvigo.arvigobasecore.ui.navigation.Screen
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme
import id.arvigo.arvigobasecore.util.Constant.IMAGE_MATRIX
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
) {
    val viewModel: ProfileViewModel = getViewModel()
    Scaffold(
        topBar = {
            DefTopBar(onMenuClick = {
                navController.navigate(Screen.ProfileEdit.route)
            })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            Log.d("neo-tag", "")
            item {
                Spacer(modifier = Modifier.padding(10.dp))
                ProfileCard()
                Spacer(modifier = Modifier.padding(10.dp))
                SubscriptionCard(
                    navController = navController
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                PersonalityCard(navController)
                Spacer(modifier = Modifier.padding(top = 12.dp))
                FaceTypeCard(navController)
                Spacer(modifier = Modifier.padding(top = 12.dp))
                ProfileRowItems(
                    navController = navController,
                    viewModel = viewModel,
                )
            }
        }
    }
}

@Composable
fun DefTopBar(onMenuClick: () -> Unit) {
    StatelessTopBar(
        navigationIcon = {},
        title = "Profile",
        actionIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Profile",
                )
            }
        }
    )
}

@Composable
fun ProfileCard() {
    val viewModel: ProfileViewModel = getViewModel()
    CustomRowTwo(
        image = IMAGE_MATRIX,
        textOne = viewModel.fullName.toString(),
        textTwo = viewModel.email.toString()
    )
}

@Composable
fun SubscriptionCard(
    navController: NavController
) {
    val viewModel: ProfileViewModel = getViewModel()
    val subscribed = if (viewModel.isSubscribed == true) {
        viewModel.isSubscribed.toString()
    } else {
        "Anda belum berlangganan."
    }
    CustomCardThree(
        title = "Langganan", desc = subscribed,
        button = "Lihat Harga",
        onClick = {
            navController.navigate(Screen.Pricing.route)
        }
    )
}

@Composable
fun PersonalityCard(
    navController: NavController
) {
    val viewModel: ProfileViewModel = getViewModel()
    val personalityModel : PersonalityViewModel = getViewModel()
    val model = ""//personalityModel.testP.toString()
    val openDialog = remember { mutableStateOf(false) }
    val testValue = if (viewModel.isPersonalityTest == true) {
        viewModel.isPersonalityTest.toString()
    } else {
        "Belum melakukan test personalitas."
    }
    CustomCardThree(
        title = "Personality",
        desc = testValue,
        button = "Lihat",
        onClick = { navController.navigate(Screen.Personality.route) }
    )
    if (openDialog.value) {
        AlertFeatureUnavailable(openDialog = openDialog)
    }
}

@Composable
fun FaceTypeCard(
    navController: NavController
) {
    val viewModel : ProfileViewModel = getViewModel()
    val faceShape = if (viewModel.faceShape.toString() == "null") {
        "Belum melakukan test muka."
    } else {
        viewModel.faceShape.toString()
    }
    CustomCardThree(
        title = "Tipe Muka",
        desc = faceShape,
        button = "Ubah",
        onClick = { navController.navigate(Screen.FaceShapeIntro.route) }
    )
}

@Composable
fun ProfileRowItems(
    navController: NavController,
    viewModel: ProfileViewModel,
) {
    val openDialog = remember { mutableStateOf(false) }
    val unavailableDialog = remember { mutableStateOf(false) }
    val aboutDialog = remember { mutableStateOf(false) }
    MenuRowItem(name = "Tentang Aplikasi", onMenuClick = { aboutDialog.value = true })
    if (aboutDialog.value) {
        AlertAbout(openDialog = aboutDialog)
    }
    MenuRowItem(name = "Logout", onMenuClick = { openDialog.value = true })
    if (openDialog.value) {
        AlertLogout(openDialog = openDialog) {
            openDialog.value = false
            viewModel.logout()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Profile.route) {
                    inclusive = true
                }
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        }
    }
    if (unavailableDialog.value) {
        AlertFeatureUnavailable(openDialog = unavailableDialog)
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ArvigoBaseCoreTheme {
/*        ProfileScreen(
            navController = navController
        )*/
    }
}