package br.edu.utfpr.appcontatos

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.appcontatos.ui.theme.AppContatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContatosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Samuel",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color  = MaterialTheme.colorScheme.primary.copy(alpha = 50f), //o copy usa o objeto e apenas adiciona algo a mais nele
        style  = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
    )
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true) // cria outra vizualização do componente
@Preview(showBackground = true, heightDp = 400, widthDp = 400, showSystemUi = true)// cria a preview do componente
@Composable
fun GreetingPreview() {
    AppContatosTheme {
        Greeting(
            name = "Android",
            modifier = Modifier.padding(40.dp).padding(top = 10.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
            //modifier = Modifier.fillMaxSize()
        )
    }
}