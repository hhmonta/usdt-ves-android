package com.usdtves.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val TetherGreen = Color(0xFF50AF95)
val TetherGreenDark = Color(0xFF3D8B74)
val TetherGreenLight = Color(0xFF7CC4B2)
val BolivarGold = Color(0xFFFFB300)
val SellRed = Color(0xFFEF5350)
val BuyGreen = Color(0xFF4CAF50)
val DarkSurface = Color(0xFF1A1A2E)
val DarkBackground = Color(0xFF0F0F1A)
val DarkCard = Color(0xFF252540)

private val DarkColorScheme = darkColorScheme(
    primary = TetherGreen,
    secondary = BolivarGold,
    tertiary = TetherGreenLight,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    surfaceVariant = DarkCard,
    onSurfaceVariant = Color(0xFFB0B0C0),
)

private val LightColorScheme = lightColorScheme(
    primary = TetherGreenDark,
    secondary = Color(0xFFE65100),
    tertiary = TetherGreenLight,
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE8F5E9),
    onSurfaceVariant = Color(0xFF495057),
)

@Composable
fun USDTVESTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
