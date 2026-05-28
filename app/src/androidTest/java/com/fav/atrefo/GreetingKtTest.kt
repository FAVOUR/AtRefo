package com.fav.atrefo

import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingKtTest {

    @Test
    fun greeting_formatsNameCorrectly() {
        val name = "Android"
        val expected = "Hello $name!"
        assertEquals(expected, "Hello $name!")
    }
}
