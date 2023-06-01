package id.arvigo.arvigobasecore.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StatelessTopBar(
    navigationIcon: @Composable () -> Unit,
    title: String,
    actionIcon: @Composable RowScope.() -> Unit
) {
    SmallTopAppBar(
        navigationIcon = navigationIcon,
        title = { Text(text = title) },
        actions = actionIcon,
    )
}