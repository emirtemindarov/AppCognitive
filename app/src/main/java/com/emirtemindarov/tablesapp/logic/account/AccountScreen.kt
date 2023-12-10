package com.emirtemindarov.tablesapp.logic.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.emirtemindarov.tablesapp.R
import com.emirtemindarov.tablesapp.logic.login.UserData
import com.emirtemindarov.tablesapp.logic.scaffold.StatisticsDialog

@Composable
fun AccountScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    //sharedViewModel: ScaffoldViewModel
) {
    val showDialog = remember { mutableStateOf(false) }
    val openDialog: () -> Unit = {
        showDialog.value = true
    }
    val closeDialog: () -> Unit = {
        showDialog.value = false
    }
    if (showDialog.value) {
        StatisticsDialog(closeDialog)
    }

    var iconsVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .size(150.dp)
                    //.border(BorderStroke(2.dp, Color.Red))
            )
            // TODO Кнопка настроек
            AnimatedVisibility(visible = iconsVisibility) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .clickable {
                            // TODO
                        }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedVisibility(visible = iconsVisibility) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_settings_24),
                                contentDescription = "Account settings icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .size(10.dp, 150.dp)
                    //.border(BorderStroke(2.dp, Color.Red))
            )

            // Кнопка выхода из аккаунта
            AnimatedVisibility(visible = iconsVisibility) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .size(40.dp, 50.dp)
                            //.border(BorderStroke(2.dp, Color.Red))
                    )
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.primary)
                            .clickable(onClick = onSignOut)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_logout_24),
                                contentDescription = "Account Sign-out icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(3f)
                //.border(BorderStroke(2.dp, Color.Red))
                .fillMaxSize(),
        ) {
            if (userData != null) {
                userData.profilePictureUrl?.let {
                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(220.dp)
                            .clip(CircleShape)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    //onPress = { /* Called when the gesture starts */ },
                                    //onTap = { /* Called on Double Tap */ },
                                    //onDoubleTap = { /* Called on Double Tap */ },
                                    onLongPress = { iconsVisibility = !iconsVisibility },
                                )
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                userData.username?.let {
                    Text(
                        text = userData.username,
                        textAlign = TextAlign.Center,
                        fontSize = (50-(userData.username.length)).sp,
                        //fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            } else {
                iconsVisibility = true
            }
        }

        Column(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize()
                //.border(BorderStroke(2.dp, Color.Red))
                .padding(60.dp, 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = openDialog, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)) {
                Text(text = "Просмотреть статистику", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {}, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)) {
                Text(text = "Пройти тест", fontSize = 18.sp)
            }
        }
    }
}