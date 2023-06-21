package dev.anmatolay.artistarchivist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.anmatolay.artistarchivist.R

val foundationTitleFamily = FontFamily(
    Font(R.font.foundationtitles_medium, FontWeight.Medium),
    Font(R.font.foundationtitles_semibold, FontWeight.SemiBold),
)
val foundationLogoFamily = FontFamily(Font(R.font.foundationlogo_medium, FontWeight.Medium))

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = foundationTitleFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = foundationTitleFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = foundationLogoFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )
)
