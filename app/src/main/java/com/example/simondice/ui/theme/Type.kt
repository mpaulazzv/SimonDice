package com.example.simondice.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.simondice.R

//Font Family de Oswald

val oswald = FontFamily(
    Font(R.font.oswald_regular, FontWeight.Normal),
    Font(R.font.oswald_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    //Tipografía para texto grande (Oswald Regular)
    bodyLarge = TextStyle(
        fontFamily = oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    //Tipografía para texto pequeño (Oswald Regular)
    bodySmall = TextStyle(
        fontFamily = oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    //Tipografía para títulos grandes (Oswald Bold)
    titleLarge = TextStyle(
        fontFamily = oswald,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    //Tipografía para títulos medianos (Oswald Bold)
    titleMedium = TextStyle(
        fontFamily = oswald,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

)