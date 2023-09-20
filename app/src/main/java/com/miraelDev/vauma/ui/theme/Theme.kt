package com.guru.composecookbook.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.miraelDev.vauma.ui.theme.Black
import com.miraelDev.vauma.ui.theme.DarkGreen
import com.miraelDev.vauma.ui.theme.DarkWhite700
import com.miraelDev.vauma.ui.theme.Green
import com.miraelDev.vauma.ui.theme.LightBlack
import com.miraelDev.vauma.ui.theme.Shapes
import com.miraelDev.vauma.ui.theme.Typography

// dark/light green
private val LightMainColorPalette = lightColors(
        primary = Green,
        background = Color.White,
        onSurface = DarkWhite700,
        onBackground = Color.Black,
)


private val DarkMainColorPalette = darkColors(
        primary = DarkGreen,
        background = Black,
        onSurface = LightBlack,
        onBackground = Color.White,
)

@Composable
fun HikariTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
) {

    MaterialTheme(
            colors = if (darkTheme) DarkMainColorPalette else LightMainColorPalette,
            shapes = Shapes,
            typography = Typography,
            content = content
    )
}
