package com.fav.atrefo.ui.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier

// Non-restartable: a one-Text wrapper, where the generated skip check costs more than
// simply re-running the body. Recomposition is handled by the calling scope.
@NonRestartableComposable
@Composable
fun GreetingScreen(name: String, modifier: Modifier = Modifier) {
    Text(
        text = greetStringTest(name),
        modifier = modifier,
    )
}
