package id.arvigo.arvigobasecore.ui.feature.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.components.EmailTextField
import id.arvigo.arvigobasecore.ui.components.NameTextField
import id.arvigo.arvigobasecore.ui.components.PasswordTextField
import id.arvigo.arvigobasecore.ui.components.UsernameTextField
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

@Composable
fun RegisterScreen() {
    RegisterScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo),
                    contentDescription = "Arvigo Logo",
                    modifier = Modifier
                        .width(200.dp)
                        .padding(20.dp)
                )
                Text(text = "Let's Get Started", style = MaterialTheme.typography.titleLarge)
                Text(text = "Create a New Account", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
                Spacer(modifier = Modifier.padding(16.dp))
                NameTextField()
                Spacer(modifier = Modifier.padding(16.dp))
                UsernameTextField()
                Spacer(modifier = Modifier.padding(16.dp))
                EmailTextField()
                Spacer(modifier = Modifier.padding(16.dp))
                PasswordTextField()
                Spacer(modifier = Modifier.padding(16.dp))
            }
            Button(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Register")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ArvigoBaseCoreTheme {
        RegisterScreen()
    }
}