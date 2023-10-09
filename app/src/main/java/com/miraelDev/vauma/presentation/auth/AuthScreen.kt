package com.miraelDev.vauma.presentation.auth

import androidx.compose.runtime.Composable
import com.miraelDev.vauma.navigation.AuthNavGraph
import com.miraelDev.vauma.navigation.rememberNavigationState
import com.miraelDev.vauma.presentation.auth.signInScreen.SignInScreen
import com.miraelDev.vauma.presentation.auth.signUpScreen.SignUpScreen

@Composable
fun AuthScreen() {

    val navigationState = rememberNavigationState()

    AuthNavGraph(
        navHosController = navigationState.navHostController,
        signInScreenContent = {
            SignInScreen(
                navigateToSignUpScreen = {
                    navigationState.navigateToSignUpScreen()
                }
            )
        },
        signUpScreenContent = {
            SignUpScreen(
                onBackPressed = {
                    navigationState.navHostController.popBackStack()
                }
            )
        }
    )
}