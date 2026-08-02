package com.fav.atrefo.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
     */
)

// The API-31 guard now lives in chooseColorScheme(), which lint cannot follow across
// the enum. DYNAMIC_* is only ever returned when supportsDynamicColor is true.
@SuppressLint("NewApi")
// Non-restartable: a root wrapper composed once. Without its own restart scope, a system
// dark-mode change recomposes AtrefoApp's scope instead of this one - one extra scope on
// an event the user triggers by hand, and the subtree still skips where it can.
@NonRestartableComposable
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = when (chooseColorScheme(darkTheme, dynamicColor)) {
        ColorSchemeChoice.DYNAMIC_DARK -> dynamicDarkColorScheme(context)
        ColorSchemeChoice.DYNAMIC_LIGHT -> dynamicLightColorScheme(context)
        ColorSchemeChoice.STATIC_DARK -> DarkColorScheme
        ColorSchemeChoice.STATIC_LIGHT -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

/** The colour scheme a given configuration resolves to. */
internal enum class ColorSchemeChoice {
    DYNAMIC_DARK,
    DYNAMIC_LIGHT,
    STATIC_DARK,
    STATIC_LIGHT,
}

/**
 * Decides the colour scheme.
 *
 * Kept out of the composable so every combination can be exercised on the JVM —
 * including [supportsDynamicColor] = false, which an instrumented test cannot reach
 * on an API 31+ device, and which is therefore invisible to the emulator suite.
 */
internal fun chooseColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    supportsDynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
): ColorSchemeChoice = when {
    dynamicColor && supportsDynamicColor ->
        if (darkTheme) ColorSchemeChoice.DYNAMIC_DARK else ColorSchemeChoice.DYNAMIC_LIGHT

    darkTheme -> ColorSchemeChoice.STATIC_DARK
    else -> ColorSchemeChoice.STATIC_LIGHT
}
