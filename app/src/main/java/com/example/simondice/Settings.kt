package com.example.simondice

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.ui.theme.white

@Composable
fun Settings() {

    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f))) {

        Box(modifier = Modifier.matchParentSize()) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                //Contenedor de todas las opciones
                Box(modifier = Modifier.width(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(400.dp)
                    .background(Color(0xFFFFD971))

                ){
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.padding(5.dp))
                        Box(){
                            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                Image(painter = painterResource(id = R.drawable.close)
                                    ,contentDescription = ""
                                    , modifier = Modifier.size(30.dp).rotate(45f)
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                            }
                            Row (modifier = Modifier.fillMaxWidth().padding(0.dp,10.dp,0.dp,0.dp),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Image(painter = painterResource(id = R.drawable.settings)
                                    ,contentDescription = ""
                                    , modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(text = stringResource(R.string.settings),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))

                        //Sección sonido y música
                        Box(modifier = Modifier.fillMaxWidth(0.9f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(10.dp)
                        ){
                            Column(modifier = Modifier.fillMaxWidth()) {
                                //Sonido
                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically){
                                    Image(painter = painterResource(id = R.drawable.sound_loud_filled_svgrepo_com)
                                        ,contentDescription = ""
                                        , modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text(text = stringResource(R.string.sonido),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    SliderMinimalExample_sound()
                                }
                                Spacer(modifier = Modifier.padding(8.dp))

                                //Musica
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Image(painter = painterResource(id = R.drawable.music_svgrepo_com)
                                        ,contentDescription = ""
                                        , modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text(text = stringResource(R.string.musica),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    SliderMinimalExample_music()
                                }

                            }

                        }
                        Spacer(modifier = Modifier.padding(10.dp))

                        //Sección notificaciones y como jugar
                        Box(modifier = Modifier.fillMaxWidth(0.9f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(10.dp)
                        ){
                            Column(modifier = Modifier.fillMaxWidth()) {
                                //notificaciones
                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically){
                                    Image(painter = painterResource(id = R.drawable.notification_13_svgrepo_com)
                                        ,contentDescription = ""
                                        , modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text(text = stringResource(R.string.notif),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                        CheckboxMinimalExample()
                                    }
                                }
                                Spacer(modifier = Modifier.padding(8.dp))

                                //Como jugar
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Image(painter = painterResource(id = R.drawable.information_circle_svgrepo_com)
                                        ,contentDescription = ""
                                        , modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text(text = stringResource(R.string.como_jugar),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                                        button_howtoplay()
                                    }

                                }

                            }

                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        playaudio()
                    }
                }
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderMinimalExample_sound() {
    val context = LocalContext.current
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val (sliderValue, setSliderValue) = remember { mutableFloatStateOf(currentVolume.toFloat())}

    Column {
        Slider(
            value = sliderValue,
            valueRange = 0f..1f,
            onValueChange = {
                value ->
                setSliderValue(value)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFF719EFF),
                inactiveTickColor = Color(0xFF719EFF).copy(
                    alpha = 0.4f
                )
            ),
//            thumb = {
//                SliderDefaults.Thumb(
//                    interactionSource = remember {
//                        MutableInteractionSource()
//                    },
//                    thumbSize = DpSize(35.dp,35.dp),
//                    colors = SliderDefaults.colors(
//                        thumbColor = MaterialTheme.colorScheme.surface
//                    )
//                )
//            }

        )
        //Text(text = sliderPosition.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderMinimalExample_music() {
    val context = LocalContext.current
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val (sliderValue, setSliderValue) = remember { mutableFloatStateOf(currentVolume.toFloat())}

    Column {
        Slider(
            value = sliderValue,
            valueRange = 0f..1f,
            onValueChange = { value ->
                setSliderValue(value)
                val newVolume = (value * maxVolume).toInt()
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    newVolume,
                    0
                )
            },
            colors = SliderDefaults.colors(
                activeTrackColor = Color(0xFF719EFF),
                inactiveTickColor = Color(0xFF719EFF).copy(
                    alpha = 0.4f
                )
            ),
//            thumb = {
//                SliderDefaults.Thumb(
//                    interactionSource = remember {
//                        MutableInteractionSource()
//                    },
//                    thumbSize = DpSize(35.dp,35.dp),
//                    colors = SliderDefaults.colors(
//                        thumbColor = MaterialTheme.colorScheme.surface
//                    )
//                )
//            }

        )
        //Text(text = sliderPosition.toString())
    }
}

@Composable
fun CheckboxMinimalExample() {
    var checked by remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
//        Text(
//            "Minimal checkbox"
//        )
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
    }

//    Text(
//        if (checked) "Checkbox is checked" else "Checkbox is unchecked"
//    )
}

@Composable
fun button_howtoplay() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(38.dp),
        colors = ButtonDefaults.buttonColors(
            Color(0xFF95BDFF),
            Color.White,
            Color.Gray,
            Color.White
        )
    )
    {
        Text(text = stringResource(R.string.btn_cj))
    }
}

@Composable
fun playaudio(){
    val mp: MediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.song)

    Row(Modifier.fillMaxWidth()) {
        Button(
            onClick = { mp.start()},
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(38.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF95BDFF),
                Color.White,
                Color.Gray,
                Color.White
            )
        )
        {
            Text(text = "sonar")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {mp.pause()},
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(38.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF95BDFF),
                Color.White,
                Color.Gray,
                Color.White
            )
        )
        {
            Text(text = "Pausar")
        }
    }
}
