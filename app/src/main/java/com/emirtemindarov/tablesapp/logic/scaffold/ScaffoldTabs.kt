package com.emirtemindarov.tablesapp.logic.scaffold

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.emirtemindarov.tablesapp.logic.login.UserData
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Tab1(sharedViewModel: ScaffoldViewModel) {
    Log.i("tab_1_button_before", "${sharedViewModel.scaffoldState}")
    val collectedState by sharedViewModel.scaffoldState.collectAsStateWithLifecycle()
    Log.i("tab_1_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_1_button_after", "${sharedViewModel.scaffoldState.value.counter}")
            Log.i("tab_1_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка1Прибавить")
        }
    }
}

@Composable
fun Tab2(sharedViewModel: ScaffoldViewModel) {
    Log.i("tab_2_button_before", "${sharedViewModel.scaffoldState}")
    val collectedState by sharedViewModel.scaffoldState.collectAsStateWithLifecycle()
    Log.i("tab_2_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_2_button_after", "${sharedViewModel.scaffoldState.value.counter}")
            Log.i("tab_2_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка2Прибавить")
        }
    }
}

@Composable
fun Tab3(userData: UserData?,
         onSignOut: () -> Unit,
         sharedViewModel: ScaffoldViewModel
) {
    Log.i("tab_3_button_before", "${sharedViewModel.scaffoldState}")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if(userData?.username != null) {
                Text(
                    text = userData.username,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Button(onClick = onSignOut) {
                Text(text = "Sign out")
            }
        }
}