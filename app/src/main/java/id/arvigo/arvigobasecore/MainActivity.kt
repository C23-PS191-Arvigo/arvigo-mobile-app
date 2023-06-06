package id.arvigo.arvigobasecore

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import id.arvigo.arvigobasecore.ui.feature.deepAR.DeepArActivity
import id.arvigo.arvigobasecore.ui.theme.ArvigoBaseCoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArvigoBaseCoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   JetArvigoApp()
                }
            }
        }
        startDeepARActivity()
    }
    private fun startDeepARActivity() {
        val intent = Intent(this, DeepArActivity::class.java)
        startActivity(intent)
    }
}