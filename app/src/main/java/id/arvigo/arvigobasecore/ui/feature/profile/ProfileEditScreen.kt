package id.arvigo.arvigobasecore.ui.feature.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.EmailTextField
import id.arvigo.arvigobasecore.ui.component.NameTextField
import id.arvigo.arvigobasecore.ui.component.PasswordTextField
import id.arvigo.arvigobasecore.ui.component.RePasswordTextField
import id.arvigo.arvigobasecore.ui.component.StatelessTopBar
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen() {
    Scaffold(
        topBar = { ProfileEditTopBar(onMenuClick = {}) {} }
    ) {
        var emailState by remember { mutableStateOf("") }
        var passwordState by remember { mutableStateOf("") }
        var confirmPasswordState by remember { mutableStateOf("") }
        var fullNameState by remember { mutableStateOf("") }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.padding(8.dp))
                ProfileEdit()
                Spacer(modifier = Modifier.padding(8.dp))
                NameTextField(value = fullNameState, onValueChange = {fullNameState = it})
                Spacer(modifier = Modifier.padding(8.dp))
                EmailTextField(value = emailState, onValueChange = {emailState = it})
                Spacer(modifier = Modifier.padding(8.dp))
                PasswordTextField(value = passwordState, onValueChange = {passwordState = it}, placeHolder = "Password")
                Spacer(modifier = Modifier.padding(8.dp))
                RePasswordTextField(value = confirmPasswordState, onValueChange = {confirmPasswordState = it})
            }
        }
    }
}

@Composable
fun ProfileEdit() {
    Box(
        modifier = Modifier
            .width(80.dp)
            .padding(horizontal = 4.dp, vertical = 4.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://media.wired.com/photos/5ca648a330f00e47fd82ae77/master/w_2560%2Cc_limit/Culture_Matrix_Code_corridor.jpg")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.img_placeholder),
            alignment = Alignment.Center,
            modifier = Modifier.clip(CircleShape)
        )
    }
}

@Composable
fun ProfileEditTopBar(
    onMenuClick: () -> Unit,
    onActionClick: () -> Unit
) {
    StatelessTopBar(
        navigationIcon = {
            IconButton(onClick =
            { onMenuClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        title = "Personal Data",
        actionIcon = {
            IconButton(onClick = onActionClick ) {
                Text(
                    text = "Save",
                    style = TextStyle.Default
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileEditPreview() {
    ArvigoBaseCoreTheme {
        ProfileEditScreen()
    }
}