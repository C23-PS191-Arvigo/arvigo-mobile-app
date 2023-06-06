package id.arvigo.arvigobasecore.ui.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.style.TextAlign
import id.arvigo.arvigobasecore.ui.navigation.Screen

@Composable
fun PrimaryAlert(
    openDialog: MutableState<Boolean>,
    ctx: Context,
    url: String,
) {
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        icon = {
               Icon(
                   imageVector = Icons.Filled.Info,
                   contentDescription = "Info",
                   tint = MaterialTheme.colorScheme.primary
               )
        },
        title = {
            Text(text = "Fitur dalam pengembangan",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            Text(text = "Mohon maaf untuk saat ini fitur yang anda pilih masih dalam tahap pengembangan. Namun anda dapat mencoba fitur ini dengan dengan browser anda.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                    ctx.startActivity(urlIntent)
                }
            ) {
                Text("Coba")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text("Kembali")
            }
        }
    )
}