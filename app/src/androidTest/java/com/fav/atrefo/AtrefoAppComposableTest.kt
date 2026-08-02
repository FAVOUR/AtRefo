package com.fav.atrefo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fav.atrefo.ui.AtrefoApp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The root composable, exercised through every combination of its defaulted
 * parameters and through recomposition.
 *
 * Compose encodes which arguments were supplied in a `$default` bitmask and branches
 * on it, so a composable only ever called one way leaves those branches half-covered.
 */
@RunWith(AndroidJUnit4::class)
class AtrefoAppComposableTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun rendersWithBothParametersDefaulted() {
        composeRule.setContent { AtrefoApp() }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()
    }

    @Test
    fun rendersWithOnlyTheNameSupplied() {
        composeRule.setContent { AtrefoApp(name = "World") }

        composeRule.onNodeWithText("Hello World").assertIsDisplayed()
    }

    @Test
    fun rendersWithOnlyTheModifierSupplied() {
        composeRule.setContent { AtrefoApp(modifier = Modifier.padding(8.dp)) }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()
    }

    @Test
    fun rendersWithBothParametersSupplied() {
        composeRule.setContent { AtrefoApp(name = "Nairobi", modifier = Modifier.padding(4.dp)) }

        composeRule.onNodeWithText("Hello Nairobi").assertIsDisplayed()
    }

    @Test
    fun recomposesWhenTheNameChanges() {
        var name by mutableStateOf("Android")
        composeRule.setContent { AtrefoApp(name = name) }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()

        composeRule.runOnIdle { name = "World" }

        composeRule.onNodeWithText("Hello World").assertIsDisplayed()
    }

    @Test
    fun innerContentIsSkippedWhenOnlyTheModifierChanges() {
        // AtrefoApp recomposes, but the Scaffold's content lambda captures only `name`,
        // so it stays equal and Compose skips it — the other half of the lambda's
        // generated skip check.
        var pad by mutableStateOf(0.dp)
        composeRule.setContent { AtrefoApp(modifier = Modifier.padding(pad)) }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()

        composeRule.runOnIdle { pad = 12.dp }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()
    }

    @Test
    fun isSkippedWhenItsInputsAreUnchanged() {
        var tick by mutableStateOf(0)
        composeRule.setContent {
            Column {
                Text("tick $tick")
                AtrefoApp()
            }
        }

        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()

        composeRule.runOnIdle { tick = 1 }

        composeRule.onNodeWithText("tick 1").assertIsDisplayed()
        composeRule.onNodeWithText("Hello Android").assertIsDisplayed()
    }
}
