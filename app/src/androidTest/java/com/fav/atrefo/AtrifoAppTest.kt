package com.fav.atrefo

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fav.atrefo.ui.theme.Pink40
import com.fav.atrefo.ui.theme.Pink80
import com.fav.atrefo.ui.theme.Purple40
import com.fav.atrefo.ui.theme.Purple80
import com.fav.atrefo.ui.theme.PurpleGrey40
import com.fav.atrefo.ui.theme.PurpleGrey80
import com.fav.atrefo.ui.theme.Typography
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AtrifoAppTest {

    @Test
    fun applicationUnderTest_isTheHiltApplication() {
        val application =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        assertTrue("expected AtrifoApp, got ${application.javaClass.name}", application is AtrifoApp)
    }
}

/**
 * The design tokens are top-level `val`s, so they are only initialised when something
 * touches them. Asserting on them keeps the palette and type ramp honest instead of
 * letting them drift as untouched constants.
 */
@RunWith(AndroidJUnit4::class)
class DesignTokensTest {

    @Test
    fun palette_definesDistinctLightAndDarkTones() {
        val dark = listOf(Purple80, PurpleGrey80, Pink80)
        val light = listOf(Purple40, PurpleGrey40, Pink40)

        (dark + light).forEach { assertNotEquals(Color.Unspecified, it) }
        dark.zip(light).forEach { (darkTone, lightTone) -> assertNotEquals(darkTone, lightTone) }
    }

    @Test
    fun typography_definesTheBodyLargeRamp() {
        val bodyLarge = Typography.bodyLarge

        assertEquals(FontWeight.Normal, bodyLarge.fontWeight)
        assertEquals(16.sp, bodyLarge.fontSize)
        assertEquals(24.sp, bodyLarge.lineHeight)
        assertEquals(0.5.sp, bodyLarge.letterSpacing)
    }
}
