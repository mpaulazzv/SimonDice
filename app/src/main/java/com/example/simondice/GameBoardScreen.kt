package com.example.simondice

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


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
        viewModelScope.launch {
            interaccion.value = false
            secuanciaJugador.clear()
            secuenciaMostrada.value = true
            addToSequence()
            showSequence()
            secuenciaMostrada.value = false
            interaccion.value = true
        }
    }

    private suspend fun showSequence() {
        delay(800)
        secuencia.forEachIndexed { index, color ->
            colorActual.value = color
            delay(800)
            colorActual.value = null
            delay(400)
        }
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
            return
        }
        secuanciaJugador.clear()
        viewModelScope.launch {
            interaccion.value = false
            delay(500) // Pequeña pausa antes de mostrar la secuencia de nuevo
            showSequence()
            interaccion.value = true
        }
    }

    private fun secuenciaCompletada() {
        puntaje.value += 10
        nivel.value += 1
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
fun GameBoardScreen(viewModel: SimonDiceViewModel = viewModel(),home: () -> Unit){

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

        Spacer(modifier = Modifier.padding(25.dp))
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

        GameBoard(colorClickeado = viewModel::colorClickeado, colorActual = colorActual, interaccion = interaccion
        )

        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = { home() },
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
                home,
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
                        isHighlighted = isHighlighted,
                        colorNro = colorActual
                    )

                }
            }
        }
    }

}

@Composable
fun GameCell(color: androidx.compose.ui.graphics.Color, painter: Painter, size:Int,
             onClick: () -> Unit, modifier: Modifier = Modifier, isHighlighted:Boolean, colorNro:Int?){

    val animatedColor by animateColorAsState(
        targetValue = if (isHighlighted) color.copy(alpha = 1f) else color.copy(alpha = 0.6f),
        label = "color"
    )

    if(colorNro == 0){
        PlayAudio(LocalContext.current, R.raw.sonic)
    }else if (colorNro == 1){
        PlayAudio(LocalContext.current, R.raw.mario)
    }else if (colorNro == 2){
        PlayAudio(LocalContext.current, R.raw.poyo)
    }else if(colorNro == 3){
        PlayAudio(LocalContext.current, R.raw.pac)
    }

    Button(
        onClick = {onClick()},
        modifier = Modifier
            .pulsateClick()
            .height(170.dp)
            .width(170.dp),
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
    home: () -> Unit,
    score: Int,
    onPlayAgain: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        title = { },
        text = {
            Surface(
                modifier = Modifier.width(300.dp).height(280.dp),
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.gameover))
                    val progress by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = LottieConstants.IterateForever
                    )

                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        modifier = Modifier.size(300.dp)
                    )
                }
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = onPlayAgain) {
                        Text("Reintentar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { home() }) {
                        Text("Home")
                    }
                }
            }
        }
    )
}

@Composable
fun PlayAudio(
    context: Context,
    audio: Int
){
    val mp: MediaPlayer = MediaPlayer.create(context, audio)
    mp.start()
    mp.setOnCompletionListener { mp.stop() }
}

