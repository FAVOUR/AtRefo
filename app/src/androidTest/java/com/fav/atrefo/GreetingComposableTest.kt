package com.fav.atrefo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fav.atrefo.ui.theme.MyApplicationTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GreetingComposableTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun greeting_displaysCorrectText() {
        composeTestRule.setContent {
            MyApplicationTheme {
                Greeting(name = "Android")
            }
        }

        composeTestRule
            .onNodeWithText("Hello Android!")
            .assertIsDisplayed()
    }

    @Test
    fun greeting_displaysCustomName() {
        composeTestRule.setContent {
            MyApplicationTheme {
                Greeting(name = "Compose")
            }
        }

        composeTestRule
            .onNodeWithText("Hello Compose!")
            .assertIsDisplayed()
    }

//    @Test
//    fun greeting_displaysEmptyName() {
//        composeTestRule.setContent {
//            MyApplicationTheme {
//                Greeting(name = "")
//            }
//        }
//
//        composeTestRule
//            .onNodeWithText("Hello  !")
//            .assertIsDisplayed()
//    }

    @Test
    fun greeting_doesNotDisplayWrongText() {
        composeTestRule.setContent {
            MyApplicationTheme {
                Greeting(name = "Android")
            }
        }

        composeTestRule
            .onNodeWithText("Hello World!")
            .assertDoesNotExist()
    }
}
