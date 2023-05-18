package id.arvigo.arvigobasecore.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { androidx.compose.material3.Text("Username") },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { androidx.compose.material3.Text("Name") },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = PasswordVisualTransformation(),
        placeholder = { androidx.compose.material3.Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun RePasswordTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = PasswordVisualTransformation(),
        placeholder = { androidx.compose.material3.Text("Retype your Password") },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    androidx.compose.material3.TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { androidx.compose.material3.Text("Email") },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}