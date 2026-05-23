package com.fav.atrefo

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    // Covers: onCreate, enableEdgeToEdge, setContent, Scaffold, Greeting composable
    @Test
    fun mainActivity_launchesAndDisplaysGreeting() {
        composeRule
            .onNodeWithText("Hello Android!")
            .assertIsDisplayed()
    }
}

/*
class GreetingComposableTest {

    @get:Rule
    val composeRule = createComposeRule()

    // Covers: Greeting() composable directly
    @Test
    fun greeting_displaysCorrectText() {
        composeRule.setContent {
            Greeting(name = "Android")
        }
        composeRule
            .onNodeWithText("Hello Android!")
            .assertIsDisplayed()
    }

    // Covers: Greeting() with different name
    @Test
    fun greeting_displaysCustomName() {
        composeRule.setContent {
            Greeting(name = "World")
        }
        composeRule
            .onNodeWithText("Hello World!")
            .assertIsDisplayed()
    }

    // Covers: GreetingPreview composable
    @Test
    fun greetingPreview_renders() {
        composeRule.setContent {
            MyApplicationTheme {
                Greeting("Android")
            }
        }
        composeRule
            .onNodeWithText("Hello Android!")
            .assertIsDisplayed()
    }

//    @Test
//    fun greeting_withExplicitModifier() {
//        composeRule.setContent {
//            Greeting(name = "Android", modifier = Modifier.padding(16.dp))
//        }
//        composeRule
//            .onNodeWithText("Hello Android!")
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun greetingPreview_isCallable() {
//        composeRule.setContent {
//            GreetingPreview() // directly invoke the preview function
//        }
//        composeRule
//            .onNodeWithText("Hello Android!")
//            .assertIsDisplayed()
//    }
}
*/
