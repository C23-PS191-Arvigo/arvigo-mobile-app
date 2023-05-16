package id.arvigo.arvigobasecore.ui.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.arvigo.arvigobasecore.R

@Composable
fun LoginScreen() {
    LoginScreenContent()
}


@Composable
fun LoginScreenContent() {
    Scaffold() {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "Welcome to Arvigo", style = MaterialTheme.typography.titleLarge)
            Text(text = "Sign In to Continue", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
            LoginForm()
        }
    }
}

@Composable
fun LoginForm() {
    TextField(value = "", onValueChange = {})
}

@Preview
@Composable
fun LoginPrev() {
    LoginForm()
}