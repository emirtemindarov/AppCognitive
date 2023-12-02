package com.emirtemindarov.tablesapp.logic.login

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)