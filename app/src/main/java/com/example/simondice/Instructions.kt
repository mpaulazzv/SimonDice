package com.example.simondice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Instructions(navToHome: () -> Unit){

    Column(

        modifier  = Modifier.fillMaxWidth().height(130.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Spacer(modifier = Modifier.height(35.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Spacer(modifier = Modifier.width(10.dp))


            Image(
                painter = painterResource(id = R.drawable.left_arrow_svgrepo_com),
                contentDescription = "back",
                modifier = Modifier.size(30.dp).clickable { navToHome() }
            )


            Spacer(modifier = Modifier.width(35.dp))

            Text(
                "INSTRUCCIONES",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 35.sp,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp
                )
            )
        }

    }
    Spacer(modifier = Modifier.height(130.dp))
    Column(
        modifier  = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(130.dp))

        Text("Descripción del juego", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Este es un juego de simon dice, que busca estimular la memoria. El objetivo es memorizar la secuencia más larga posible. La temática del juego son videojuegos " +
                "clásicos como sonic, mario, kirby y pacman, " +
                "por lo que los sonidos e imágenes son inspirados en estas franquicias. ",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify)

        Text("Elementos", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        Row(){
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "heart",
                modifier = Modifier.size(40.dp)

            )
            Spacer(modifier = Modifier.width(30.dp))
            Text("  Vidas", style = MaterialTheme.typography.bodyLarge)

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(){
            Button(
                onClick = {},
                modifier = Modifier
                    .pulsateClick()
                    .height(40.dp)
                    .width(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ){

            }

            Spacer(modifier = Modifier.width(30.dp))
            Text("  Celdas interactivas", style = MaterialTheme.typography.bodyLarge)

        }

        Spacer(modifier = Modifier.height(10.dp))
        Text("¿Cómo jugar?", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))

        Text("El tablero de juego contiene 4 celdas, las cuales tienen " +
                "diferentes colores e imágenes. " +
                "El juego comienza una vez que se ingresa a la pantalla de juego. " +
                "Una celda se iluminará para indicar el inicio de la secuencia. " +
                "Después de que la secuencia termine, el jugador debe replicarla " +
                "tocando las celdas que correspondan. Si la secuencia ingresada es " +
                "correcta subirá un nivel y se le incrementarán 10 puntos al " +
                "puntaje global, de lo contrario, se le resta una vida al " +
                "jugador. El juego termina una vez que se agotan las 3 vidas " +
                "disponibles. ",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify)


    }

}