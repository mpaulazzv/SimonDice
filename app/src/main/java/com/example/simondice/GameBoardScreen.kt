package com.example.simondice

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimonDiceViewModel : ViewModel() {
    val secuencia = mutableStateListOf<Int>()
    val secuanciaJugador = mutableStateListOf<Int>()
    val secuenciaMostrada = mutableStateOf(false)
    val colorActual = mutableStateOf<Int?>(null)
    val vidas = mutableStateOf(3)
    val puntaje = mutableStateOf(0)
    val nivel = mutableStateOf(1)
    val isGameOver = mutableStateOf(false)
    val interaccion = mutableStateOf(false)

    private fun addToSequence() {
        secuencia.add((0..3).random())
    }

    fun nuevaRonda() {
        interaccion.value = false
        secuanciaJugador.clear()
        viewModelScope.launch {
            secuenciaMostrada.value = true
            addToSequence()
            showSequence()
            secuenciaMostrada.value = false
            interaccion.value = true
        }
    }

    private suspend fun showSequence() {
        delay(800)
        secuencia.forEach { color ->
            colorActual.value = color
            delay(800)
        }
        colorActual.value = null
        delay(400)
    }

    fun colorClickeado(color: Int) {
        if (interaccion.value && !isGameOver.value) {
            secuanciaJugador.add(color)


            if (secuanciaJugador[secuanciaJugador.size - 1] != secuencia[secuanciaJugador.size - 1]) {
                errorJugador()
                return
            }

            if (secuencia.size == secuanciaJugador.size) {
                secuenciaCompletada()
            }
        }
    }

    private fun errorJugador() {
        vidas.value--
        if (vidas.value <= 0) {
            isGameOver.value = true
        }
        secuanciaJugador.clear()
        if (!isGameOver.value) {
            interaccion.value = false
            nuevaRonda()
        }
    }

    private fun secuenciaCompletada() {
        puntaje.value += 10
        nivel.value += 1
        secuanciaJugador.clear()
        interaccion.value = false
        nuevaRonda()
    }

    fun nuevoJuego() {
        secuencia.clear()
        secuanciaJugador.clear()
        vidas.value = 3
        puntaje.value = 0
        nivel.value = 1
        isGameOver.value = false
        interaccion.value = false
        nuevaRonda()
    }
}


@Composable()
fun GameBoardScreen(viewModel: SimonDiceViewModel = SimonDiceViewModel()){

    val colorActual by remember { viewModel.colorActual }
    val secuenciaMostrada by remember { viewModel.secuenciaMostrada }
    val hearts by remember { viewModel.vidas }
    val puntaje by remember { viewModel.puntaje }
    val isGameOver by remember { viewModel.isGameOver }
    val nivel by remember { viewModel.nivel }
    val interaccion by remember {viewModel.interaccion}

    LaunchedEffect(Unit) {
        viewModel.nuevoJuego()
    }

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
            Row(){
                Image(
                    painter = painterResource(id = R.drawable.medal_ribbon_star_svgrepo_com),
                    contentDescription = "Medalla",
                    modifier = Modifier.size(40.dp)
                )
            }
            //Nivel
            Text("Lvl: $nivel", style = MaterialTheme.typography.bodySmall)

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
        Text("Puntaje: $puntaje", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(80.dp))

        GameBoard(colorClickeado = {color ->
            if(!secuenciaMostrada){
                viewModel.colorClickeado(color)
            }
        }, colorActual = colorActual, interaccion = interaccion
        )

        Spacer(modifier = Modifier.height(60.dp))
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

        if (isGameOver) {
            GameOverDialog(
                score = puntaje,
                onPlayAgain = {
                    viewModel.nuevoJuego()
                }
            )
        }

    }

}
@Composable
fun GameBoard(colorClickeado: (Int) -> Unit, colorActual: Int?, interaccion:Boolean){

    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.surface
    )

    val images = listOf(
        painterResource(id = R.drawable.sonic3),
        painterResource(id = R.drawable.mario),
        painterResource(id = R.drawable.kirby),
        painterResource(id = R.drawable.pacman)
    )

    val sizes = listOf(70,60,60,60)

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
                    val color = row * 2 + col
                    val isHighlighted = colorActual == color

                    GameCell(
                        color = colors[color],
                        painter = images[color],
                        size = sizes[color],
                        onClick = {
                            if (interaccion) {
                                colorClickeado(color)
                            }
                        },
                        isHighlighted = isHighlighted
                    )

                }
            }
        }
    }

}

@Composable
fun GameCell(color: androidx.compose.ui.graphics.Color, painter: Painter, size:Int,
             onClick: () -> Unit, modifier: Modifier = Modifier, isHighlighted:Boolean){

    val animatedColor by animateColorAsState(
        targetValue = if (isHighlighted) color.copy(alpha = 1f) else color.copy(alpha = 0.6f),
        label = "color"
    )


    Button(
        onClick = onClick,
        modifier = Modifier.pulsateClick().height(170.dp).width(170.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = animatedColor)
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
@Composable
fun GameOverDialog(
    score: Int,
    onPlayAgain: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("¡Game Over!") },
        text = { Text("Puntaje final: $score") },
        confirmButton = {
            Button(onClick = onPlayAgain) {
                Text("Jugar de nuevo")
            }
        }
    )
}
