package com.fav.atrefo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fav.atrefo.ui.theme.MyApplicationTheme

// Ask AI if this is the best way to have the composable and the activity exist in such a way that
// it is testable 100% code and line coverage
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = greetingText("$name!"),
        modifier = modifier,
    )
}

// In MainActivity.kt
fun greetingText(name: String) = "Hello $name"

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Previews should not be written in fragments...They should have the composable function and the
    // preview in the same file. not in the fragment file
    MyApplicationTheme {
        Greeting("Android")
    }
}
