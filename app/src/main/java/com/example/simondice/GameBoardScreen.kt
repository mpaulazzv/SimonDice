package com.example.simondice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable()
fun GameBoardScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        //Barra superior
        Spacer(modifier = Modifier.height(60.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            //Contiene img de la medalla
            Row(){
                Image(
                    painter = painterResource(id = R.drawable.medal_ribbon_star_svgrepo_com),
                    contentDescription = "Medalla",
                    modifier = Modifier.size(40.dp)
                )
            }
            //Nivel
            Text("Lvl: ##", style = MaterialTheme.typography.bodySmall)

            //Tres usuarios
            Row(){
                Image(
                    painter = painterResource(id = R.drawable.user_svgrepo_com),
                    contentDescription = "user",
                    modifier = Modifier.size(25.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.user_svgrepo_com),
                    contentDescription = "user",
                    modifier = Modifier.size(25.dp)
                )

            }

        }

        Spacer(modifier = Modifier.height(50.dp))
        //Puntaje

        Text("Puntaje: ##", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(80.dp))
        //Espacio para el tablero de juego

        GameBoard()

        Spacer(modifier = Modifier.height(60.dp))
        //Botón de regresar al menú

        Button(
            onClick = {/*funcionalidad para regresar al menú*/},
            modifier = Modifier.height(40.dp).width(172.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor =
            MaterialTheme.colorScheme.primary)
        ){
            Text("Volver al menú",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.align(Alignment.CenterVertically))
        }

    }

}
@Composable
fun GameBoard(){

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        for (row in 0 until 2) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
            ) {
                for (col in 0 until 2) {

                    when (row){
                        0 -> {
                            when (col){
                                0->  GameCell(MaterialTheme.colorScheme.primary, painterResource(R.drawable.sonic3), 70)
                                1->  GameCell(MaterialTheme.colorScheme.tertiary, painterResource(R.drawable.mario),60)
                            }
                        }

                        1 -> {
                            when (col){
                                0-> GameCell(MaterialTheme.colorScheme.secondary, painterResource(R.drawable.kirby),60)
                                1-> GameCell(MaterialTheme.colorScheme.surface, painterResource(R.drawable.pacman),60)
                            }
                    }
                }

                }
            }
        }
    }

}

@Composable
fun GameCell(color: androidx.compose.ui.graphics.Color, painter:Painter, size:Int){

    Button(
        onClick = {/*funcionalidad al clickear la celda*/},
        modifier = Modifier.size(170.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ){
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.height(size.dp)
        )
    }

}


