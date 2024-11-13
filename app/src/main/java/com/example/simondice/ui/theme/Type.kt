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

    //Tipografía para texto normal (Oswald Regular)
    bodyLarge = TextStyle(
        fontFamily = oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    //Tipografía para títulos (Oswald Bold)
    titleLarge = TextStyle(
        fontFamily = oswald,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )

)