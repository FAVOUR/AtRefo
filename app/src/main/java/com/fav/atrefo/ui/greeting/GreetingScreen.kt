package com.fav.atrefo.ui.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GreetingScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = greetStringTest(name),
        modifier = modifier,
    )
}
