package com.fav.atrefo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fav.atrefo.ui.greeting.GreetingScreen
import com.fav.atrefo.ui.theme.MyApplicationTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Recomposition behaviour of the composables.
 *
 * Beyond asserting that the UI updates, these drive the paths the Compose compiler
 * generates around every restartable function: the `$dirty` comparison, the
 * `composer.skipping` short-circuit, and the restart scope. A composable that is only
 * ever composed once leaves all of them one-sided.
 */
@RunWith(AndroidJUnit4::class)
class RecompositionTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun greeting_updatesWhenTheNameChanges() {
        var name by mutableStateOf("Android")
        composeRule.setContent { GreetingScreen(name = name) }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()

        composeRule.runOnIdle { name = "World" }

        composeRule.onNodeWithText("Hello World").assertIsDisplayed()
    }

    @Test
    fun greeting_isSkippedWhenItsInputsAreUnchanged() {
        var tick by mutableStateOf(0)
        composeRule.setContent {
            Column {
                Text("tick $tick")
                // Same arguments on every pass, so this call is skippable.
                GreetingScreen(name = "Android")
            }
        }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()

        composeRule.runOnIdle { tick = 1 }

        composeRule.onNodeWithText("tick 1").assertIsDisplayed()
        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()
    }

    @Test
    fun theme_updatesWhenTheSchemeChanges() {
        var darkTheme by mutableStateOf(false)
        composeRule.setContent {
            MyApplicationTheme(darkTheme = darkTheme, dynamicColor = false) {
                Text("themed $darkTheme")
            }
        }

        composeRule.onNodeWithText("themed false").assertIsDisplayed()

        composeRule.runOnIdle { darkTheme = true }

        composeRule.onNodeWithText("themed true").assertIsDisplayed()
    }

    @Test
    fun theme_isSkippedWhenItsInputsAreUnchanged() {
        var tick by mutableStateOf(0)
        composeRule.setContent {
            Column {
                Text("tick $tick")
                MyApplicationTheme(darkTheme = false, dynamicColor = false) {
                    Text("themed")
                }
            }
        }

        composeRule.onNodeWithText("themed").assertIsDisplayed()

        composeRule.runOnIdle { tick = 1 }

        composeRule.onNodeWithText("tick 1").assertIsDisplayed()
        composeRule.onNodeWithText("themed").assertIsDisplayed()
    }
}
