package com.fav.atrefo.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fav.atrefo.ui.greeting.GreetingScreen
import com.fav.atrefo.ui.theme.MyApplicationTheme

class GreetingScreenPreviews {

    @Preview(showBackground = true)
    @Composable
    fun GreetingScreenPreview() { // private = excluded from coverage naturally
        MyApplicationTheme {
            GreetingScreen("Android")
        }
    }
}
