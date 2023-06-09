package id.arvigo.arvigobasecore.ui.component.alert

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertFeatureUnavailable(
    openDialog: MutableState<Boolean>
) {
    AlertStateless(
        openDialog = openDialog,
        confirmButton = {  },
        title = "Fitur dalam pengembangan",
        desc = "Mohon maaf untuk saat ini fitur masih dalam tahap pengembangan."
    )
}