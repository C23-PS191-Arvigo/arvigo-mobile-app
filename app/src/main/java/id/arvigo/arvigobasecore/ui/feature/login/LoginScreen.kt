package id.arvigo.arvigobasecore.ui.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.arvigo.arvigobasecore.R
import id.arvigo.arvigobasecore.data.source.network.request.LoginRequest
import id.arvigo.arvigobasecore.ui.component.PrimaryButton
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen() {
    LoginScreenContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    viewModel: LoginViewModel = getViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value
    val scaffoldState = rememberScaffoldState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null,
                modifier = Modifier.width(400.dp).height(100.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = "Welcome to Arvigo", style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ))
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = "Sign In to Continue", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray))
            Spacer(modifier = Modifier.padding(32.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailState.text,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon", tint = Color.LightGray) },
                onValueChange = {
                    viewModel.setEmail(it)
                },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "Your Email", color =  Color.LightGray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.LightGray
                ),
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordState.text,
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "passIcon", tint =  Color.LightGray) },
                onValueChange = {
                    viewModel.setPassword(it)
                },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "Password", color =  Color.LightGray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.LightGray
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.padding(60.dp))
            PrimaryButton(title = "Sign In", onClick = {
                viewModel.loginUser()
            })
            Spacer(modifier = Modifier.padding(24.dp))
            LoginCheck()
        }
    }
}


@Composable
fun LoginCheck() {
   Column(
         modifier = Modifier.fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
   ) {
       Text(
           text = "Forgot password?",
           color = MaterialTheme.colorScheme.primary,
           fontWeight = FontWeight.SemiBold,
           modifier = Modifier.clickable {  }
       )
       Spacer(modifier = Modifier.padding(top = 10.dp))
       Row(
           modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center
       ) {
           Text(text = "Don't have an account? ", color = Color.Gray)
           Text(
               text = "Register",
               color = MaterialTheme.colorScheme.primary,
               fontWeight = FontWeight.SemiBold,
               modifier = Modifier.clickable {  }
           )
       }
   }
}

@Preview(showBackground = true)
@Composable
fun LoginPrev() {
    LoginCheck()
}