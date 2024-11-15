package com.example.simondice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Credits() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.matchParentSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar()
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = stringResource(R.string.nuestro),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        //Item nombre
                        Card(
                            modifier = Modifier
                                //.padding(horizontal = 8.dp, vertical = 8.dp)
                                .width(145.dp)
                                .height(210.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp))
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.drip_aligator),
                                    contentDescription = "", contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(150.dp)
                                    //.clip(RoundedCornerShape(16.dp))
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(3.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = stringResource(R.string.tittle_M),
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Text(
                                            text = stringResource(R.string.subtittle_M),
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }
                            }
                        }
                        Card(
                            modifier = Modifier
                                //.padding(horizontal = 8.dp, vertical = 8.dp)
                                .width(145.dp)
                                .height(210.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp))
                        )  {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.drip_aligator),
                                    contentDescription = "", contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(150.dp)
                                    // .clip(RoundedCornerShape(16.dp))
                                )
                                Spacer(modifier = Modifier.padding(2.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(3.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = stringResource(R.string.tittle_J),
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Text(
                                            text = stringResource(R.string.subtittle_JC),
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }


                            }
                        }

                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Card(
                        modifier = Modifier
                            //.padding(horizontal = 8.dp, vertical = 8.dp)
                            .width(145.dp)
                            .height(210.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp))
                    )  {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.drip_aligator),
                                contentDescription = "", contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(150.dp)
                                //.clip(RoundedCornerShape(16.dp))
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = stringResource(R.string.tittle_C),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Text(
                                        text = stringResource(R.string.subtittle_JC),
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }


                        }
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = stringResource(R.string.recursos),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    section_imagenes()
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(200.dp)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    section_sonidos()
                    Spacer(modifier = Modifier.padding(5.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(200.dp)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                        item("Fondo", "pixabay", "pepito")
                    }
                    Spacer(modifier = Modifier.padding(30.dp))
                }
            }
        }
    }
}

@Composable
fun item(cosa: String, sitio: String, user: String) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(60.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = cosa,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.padding(1.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 0.dp)
            ) {
//                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "sacado de: " + sitio,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "usuario: " + user,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.tittle_credits),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFFFFD971),
            titleContentColor = Color.Black
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.back_svgrepo_com),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
    )
}


@Composable
fun section_imagenes() {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = R.drawable.images_svgrepo_com),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Imagenes",
                fontSize = 20.sp,
                color = Color(0xFF424242),
            )
        }
        Divider(
            color = Color(0xFF424242),
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(0.9f)
        )
    }
}

@Composable
fun section_sonidos() {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = R.drawable.sound_2_svgrepo_com),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "Sonidos",
                fontSize = 20.sp,
                color = Color(0xFF424242),
            )
        }
        Divider(
            color = Color(0xFF424242),
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(0.9f)
        )
    }
}