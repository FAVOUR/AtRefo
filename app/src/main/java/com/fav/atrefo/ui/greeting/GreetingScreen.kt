package com.fav.atrefo.ui.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fav.atrefo.ui.theme.MyApplicationTheme

@Composable
fun GreetingScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = format("$name!"),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingScreenPreview() { // private = excluded from coverage naturally
    MyApplicationTheme {
        GreetingScreen("Android")
    }
}
