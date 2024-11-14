package com.example.simondice

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.time.format.TextStyle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale


@Composable()
fun GameBoardScreen(){

    var hearts by remember { mutableStateOf(3) }
    var isGameOver by remember { mutableStateOf(false) }
    var controlSecuencia by remember { mutableStateOf(0) }
    var aleatorio by remember { mutableStateOf(0) }


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

            //Vidas
            Row(){
                for (h in 0 until hearts) {
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "user",
                        modifier = Modifier.size(25.dp)
                    )
                }

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

    var cellColor = remember { mutableStateOf<androidx.compose.ui.graphics.Color?>(null) }

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
fun GameCell(color: androidx.compose.ui.graphics.Color, painter: Painter, size:Int){

    Button(
        onClick = {},
        modifier = Modifier.pulsateClick().height(170.dp).width(170.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ){
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.height(size.dp)
        )
    }

}

//Estado del boton
enum class ButtonState {On, Off}

//Efecto al dar click
@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.pulsateClick() = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Off) }

    val scale by animateFloatAsState(
        targetValue = if (buttonState == ButtonState.On) 0.8f else 1f,
        label = "pulsate"
    )

    this
        .scale(scale)
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.On) {
                    waitForUpOrCancellation()
                    ButtonState.Off
                } else {
                    awaitFirstDown(false)
                    ButtonState.On
                }
            }
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { }
}
