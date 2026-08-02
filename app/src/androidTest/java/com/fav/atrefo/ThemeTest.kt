package com.fav.atrefo

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fav.atrefo.ui.theme.MyApplicationTheme
import com.fav.atrefo.ui.theme.Purple40
import com.fav.atrefo.ui.theme.Purple80
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Covers every arm of the colour-scheme `when` in [MyApplicationTheme].
 *
 * The dynamic-colour arm is reachable here because the test device is API 34
 * (>= S); the static arms are reached by opting out of dynamic colour.
 */
@RunWith(AndroidJUnit4::class)
class ThemeTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun capturePrimary(darkTheme: Boolean, dynamicColor: Boolean): Color {
        val primary = mutableStateOf(Color.Unspecified)
        composeRule.setContent {
            MyApplicationTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
                primary.value = MaterialTheme.colorScheme.primary
                Text("themed")
            }
        }
        composeRule.onNodeWithText("themed").assertIsDisplayed()
        return primary.value
    }

    @Test
    fun staticLightScheme_usesLightPrimary() {
        assertEquals(Purple40, capturePrimary(darkTheme = false, dynamicColor = false))
    }

    @Test
    fun staticDarkScheme_usesDarkPrimary() {
        assertEquals(Purple80, capturePrimary(darkTheme = true, dynamicColor = false))
    }

    @Test
    fun dynamicLightScheme_isResolvedFromTheSystem() {
        val primary = capturePrimary(darkTheme = false, dynamicColor = true)
        assertNotEquals(Color.Unspecified, primary)
    }

    @Test
    fun dynamicDarkScheme_isResolvedFromTheSystem() {
        val primary = capturePrimary(darkTheme = true, dynamicColor = true)
        assertNotEquals(Color.Unspecified, primary)
    }

    @Test
    fun defaultArguments_followTheSystemAndRender() {
        // Exercises the synthetic default-argument bridge: no darkTheme, no dynamicColor.
        composeRule.setContent {
            MyApplicationTheme {
                Text("defaults")
            }
        }
        composeRule.onNodeWithText("defaults").assertIsDisplayed()
    }
}
