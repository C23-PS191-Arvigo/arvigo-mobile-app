package id.arvigo.arvigobasecore.ui.feature.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.ui.component.textfield.EmailTextField
import id.arvigo.arvigobasecore.ui.component.textfield.NameTextField
import id.arvigo.arvigobasecore.ui.component.textfield.PasswordTextField
import id.arvigo.arvigobasecore.ui.component.textfield.RePasswordTextField
import id.arvigo.arvigobasecore.ui.feature.register.model.RegisterRequest
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun RegisterScreen() {
    RegisterScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenContent(
    viewModel: RegisterViewModel = getViewModel()
) {
    val isLoading = viewModel.isLoading.value // Collect the isLoading state as a Compose state
    val responseMessage by viewModel.responseMessage.collectAsState()
    val context = LocalContext.current // Get the current context for showing toast
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        var emailState by remember { mutableStateOf("") }
        var passwordState by remember { mutableStateOf("") }
        var confirmPasswordState by remember { mutableStateOf("") }
        var fullNameState by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize()) {
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
                Text(
                    text = "Create a New Account",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                NameTextField(
                    value = fullNameState,
                    onValueChange = { fullNameState = it }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                EmailTextField(
                    value = emailState,
                    onValueChange = { emailState = it }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                PasswordTextField(
                    value = passwordState,
                    onValueChange = { passwordState = it },
                    placeHolder = "Password"
                )
                Spacer(modifier = Modifier.padding(16.dp))
                RePasswordTextField(
                    value = confirmPasswordState,
                    onValueChange = { confirmPasswordState = it }
                )
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    CircularProgressIndicator() // Show the loading indicator in the center
                }
            } else {
                val isFormValid = fullNameState.isNotEmpty() && emailState.isNotEmpty() &&
                        (passwordState.isNotEmpty() )&& confirmPasswordState.isNotEmpty()
                Button(
                    onClick = {
                        val email = emailState
                        val password = passwordState
                        val confirmPassword = confirmPasswordState
                        val fullName = fullNameState

                        val registerRequest =
                            RegisterRequest(email, password, confirmPassword, fullName)
                        viewModel.register(registerRequest)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    enabled = isFormValid
                ) {
                    Text(text = "Register")
                }
            }
        }
        //Show Toasts
        LaunchedEffect(responseMessage) {
            responseMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.clearResponseMessage()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    ArvigoBaseCoreTheme {
        RegisterScreen()
    }
}