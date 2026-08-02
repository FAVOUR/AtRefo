package com.fav.atrefo

import com.fav.atrefo.ui.theme.ColorSchemeChoice
import com.fav.atrefo.ui.theme.chooseColorScheme
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The full truth table for the colour-scheme decision.
 *
 * On a device this can only ever be exercised with supportsDynamicColor = true, since
 * the emulator is API 34. Testing it as a plain function is the only way to reach the
 * pre-Android-12 arms at all.
 */
class ColorSchemeChoiceTest {

    private data class Case(
        val darkTheme: Boolean,
        val dynamicColor: Boolean,
        val supportsDynamicColor: Boolean,
        val expected: ColorSchemeChoice,
    )

    private val truthTable = listOf(
        Case(darkTheme = true, dynamicColor = true, supportsDynamicColor = true, expected = ColorSchemeChoice.DYNAMIC_DARK),
        Case(darkTheme = false, dynamicColor = true, supportsDynamicColor = true, expected = ColorSchemeChoice.DYNAMIC_LIGHT),
        Case(darkTheme = true, dynamicColor = true, supportsDynamicColor = false, expected = ColorSchemeChoice.STATIC_DARK),
        Case(darkTheme = false, dynamicColor = true, supportsDynamicColor = false, expected = ColorSchemeChoice.STATIC_LIGHT),
        Case(darkTheme = true, dynamicColor = false, supportsDynamicColor = true, expected = ColorSchemeChoice.STATIC_DARK),
        Case(darkTheme = false, dynamicColor = false, supportsDynamicColor = true, expected = ColorSchemeChoice.STATIC_LIGHT),
        Case(darkTheme = true, dynamicColor = false, supportsDynamicColor = false, expected = ColorSchemeChoice.STATIC_DARK),
        Case(darkTheme = false, dynamicColor = false, supportsDynamicColor = false, expected = ColorSchemeChoice.STATIC_LIGHT),
    )

    @Test
    fun everyCombinationResolvesToTheDocumentedScheme() {
        truthTable.forEach { case ->
            assertEquals(
                "darkTheme=${case.darkTheme} dynamicColor=${case.dynamicColor} " +
                    "supportsDynamicColor=${case.supportsDynamicColor}",
                case.expected,
                chooseColorScheme(case.darkTheme, case.dynamicColor, case.supportsDynamicColor),
            )
        }
    }

    @Test
    fun dynamicColourIsOnlyChosenWhenThePlatformSupportsIt() {
        val dynamic = setOf(ColorSchemeChoice.DYNAMIC_DARK, ColorSchemeChoice.DYNAMIC_LIGHT)

        truthTable.filterNot { it.supportsDynamicColor }.forEach { case ->
            assertEquals(
                "pre-Android-12 must never resolve to a dynamic scheme",
                false,
                chooseColorScheme(case.darkTheme, case.dynamicColor, case.supportsDynamicColor) in dynamic,
            )
        }
    }

    @Test
    fun theEnumExposesExactlyTheFourSchemes() {
        // Also exercises the synthetic values()/valueOf() the compiler adds to every enum.
        assertEquals(4, ColorSchemeChoice.entries.size)
        ColorSchemeChoice.entries.forEach { choice ->
            assertEquals(choice, ColorSchemeChoice.valueOf(choice.name))
        }
    }

    @Test
    fun supportsDynamicColorDefaultsToThePlatformCheck() {
        // Exercises the default-argument bridge. Build.VERSION.SDK_INT is stubbed to 0
        // on the JVM, so the platform is reported as not supporting dynamic colour.
        assertEquals(ColorSchemeChoice.STATIC_DARK, chooseColorScheme(darkTheme = true, dynamicColor = true))
        assertEquals(ColorSchemeChoice.STATIC_LIGHT, chooseColorScheme(darkTheme = false, dynamicColor = true))
    }
}
