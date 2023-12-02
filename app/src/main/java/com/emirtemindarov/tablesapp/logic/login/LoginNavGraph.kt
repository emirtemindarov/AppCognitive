package com.emirtemindarov.tablesapp.logic.login

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.emirtemindarov.tablesapp.logic.getSharedViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginNavGraph(
    applicationContext: Context,
    googleAuthUiClient: GoogleAuthUiClient,
    mainNavController: NavHostController,
    loginNavController: NavHostController = rememberNavController()
) {
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = loginNavController, startDestination = "login_nav") {
        navigation(route = "login_nav", startDestination = "login") {
            composable("login") { entry ->
                /*val sharedViewModel = entry.getSharedViewModel<SignInViewModel>(loginNavController)
                Log.i("anc_Tab1", "${sharedViewModel}")*/

                val viewModel = viewModel<SignInViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = Unit) {
                    if(googleAuthUiClient.getSignedInUser() != null) {
                        mainNavController.navigate("room_test")
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        if(result.resultCode == RESULT_OK) {
                            coroutineScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if(state.isSignInSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()

                        mainNavController.navigate("room_test")
                        viewModel.resetState()
                    }
                }

                SignInScreen(
                    state = state,
                    onSignInClick = {
                        coroutineScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    }
                )
            }
            }
            composable("registration") { entry ->
                /*val sharedViewModel = entry.getSharedViewModel<SignInViewModel>(loginNavController)
                Log.i("anc_Tab2", "${sharedViewModel}")*/

            }
            composable("change_password") { entry ->
                /*val sharedViewModel = entry.getSharedViewModel<SignInViewModel>(loginNavController)
                Log.i("anc_Tab3", "${sharedViewModel}")*/

            }
    }
}