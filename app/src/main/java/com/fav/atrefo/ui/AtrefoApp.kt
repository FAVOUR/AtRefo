package com.fav.atrefo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fav.atrefo.ui.greeting.GreetingScreen
import com.fav.atrefo.ui.theme.MyApplicationTheme

/**
 * The app's root composable.
 *
 * Lives outside [com.fav.atrefo.MainActivity] so it can be composed, recomposed and
 * skipped by tests. Content written inline in setContent {} is compiled into anonymous
 * singletons that no test can address directly.
 */
@Composable
fun AtrefoApp(name: String = "Android", modifier: Modifier = Modifier) {
    MyApplicationTheme {
        Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
            GreetingScreen(
                name = name,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
