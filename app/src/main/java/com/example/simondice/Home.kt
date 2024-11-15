package com.example.simondice

import android.content.Context
import android.media.MediaPlayer
import android.widget.Button
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.simondice.ui.theme.blue
import com.example.simondice.ui.theme.pink
import com.example.simondice.ui.theme.red
import com.example.simondice.ui.theme.white
import com.example.simondice.ui.theme.yellow


@Composable
fun Home(creditos: () -> Unit, settings: () -> Unit, juego: () -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = white
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .padding(
                    horizontal = 24.dp,
                    vertical = 0.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PlayAudio(LocalContext.current)
            Spacer(modifier = Modifier.padding(35.dp))
            TopBar(settings)
            Spacer(modifier = Modifier.padding(35.dp))
            AppIcon()
            Spacer(modifier = Modifier.padding(25.dp))
            Button(
                onClick = { juego() },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    blue,
                    Color.White,
                    Color.Gray,
                    Color.White
                )
            )
            {
                Text(text = "Jugar")
            }
            Spacer(modifier = Modifier.padding(35.dp))
            HomeMenu(creditos)
        }

    }
}

@Composable
fun TopBar(
    settings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ControlButton(settings)
        BestPoints()
    }
}

@Composable
fun ControlButton(
    settings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(48.dp),
        color = blue,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { settings() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.gear),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

@Composable
fun BestPoints(
    modifier: Modifier = Modifier
) {
    Row {
        Surface(
            modifier = modifier
                .size(48.dp),
            color = red,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.medal2),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
        Surface(
            modifier = modifier
                .height(38.dp)
                .width(140.dp)
                .align(Alignment.CenterVertically),
            color = blue,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 8.dp,
                bottomStart = 0.dp,
                bottomEnd = 8.dp
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "###",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

fun probar(mp: MediaPlayer, function: () -> Unit){
    mp.stop()
    function()
}

@Composable
fun AppIcon(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(
        label = "my_transition"
    )

    val ratio by transition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float_animation"
    )

    Column {
        Surface(
            modifier = modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally)
                .graphicsLayer {
                    scaleX = ratio
                    scaleY = ratio
                },
            color = yellow,
            shape = CircleShape
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.gamepad),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Simon Dice",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun HomeMenu(
    creditos: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MenuOpc(
            color = white,
            painter = painterResource(R.drawable.multiplayer),
            text = "Board",
            creditos = creditos
        )
        MenuOpc(
            color = pink,
            painter = painterResource(R.drawable.home),
            text = "Home",
            creditos = creditos
        )
        MenuOpc(
            color = white,
            painter = painterResource(R.drawable.information),
            text = "Info",
            creditos = creditos
        )
    }
}

@Composable
fun MenuOpc(
    modifier: Modifier = Modifier,
    color: androidx.compose.ui.graphics.Color,
    painter: Painter,
    text: String,
    creditos: () -> Unit
) {

    Column {
        Surface(
            modifier = modifier
                .height(140.dp)
                .width(110.dp)
                .align(Alignment.CenterHorizontally),
            color = color,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { creditos() },
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayAudio(
    context: Context
) {
    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.main_song)
    mp.start()
    mp.pause()
}