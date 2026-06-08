package com.fav.atrefo

import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingKtTest {
    // This test os not importamt accprdinf to the ID
    @Test
    fun greeting_formatsNameCorrectly() {
        val name = "Android!"
        val expected = "Hello $name"
//        assertEquals(expected, "Hello $name!")
        assertEquals(expected, greetingText(name))
    }
}
