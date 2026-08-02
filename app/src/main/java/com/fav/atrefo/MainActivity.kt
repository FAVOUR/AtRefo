package com.fav.atrefo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fav.atrefo.ui.AtrefoApp

// Ask AI if this is the best way to have the composable and the activity exist in such a way that
// it is testable 100% code and line coverage
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtrefoApp()
        }
    }
}

// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = greetingText("$name!"),
//        modifier = modifier,
//    )
// }

// In MainActivity.kt
// fun greetingText(name: String) = "Hello $name"
//
// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
//    // Previews should not be written in fragments...They should have the composable function and the
//    // preview in the same file. not in the fragment file
//    MyApplicationTheme {
//        Greeting("Android")
//    }
// }
