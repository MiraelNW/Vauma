package com.miraelDev.vauma.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.profileNavGraph(
    accountScreen: @Composable () -> Unit,
    editProfileContent: @Composable () -> Unit,
    notificationScreenContent: @Composable () -> Unit,
    downloadVideoScreenContent: @Composable () -> Unit,
    languageScreenContent: @Composable () -> Unit,
    privacyPolicyScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.Account.route,
        route = Screen.ProfileAndSettings.route
    ) {
        composable(route = Screen.Account.route) {
            accountScreen()
        }
        composable(route = Screen.EditProfile.route) {
            editProfileContent()
        }
        composable(route = Screen.Notifications.route) {
            notificationScreenContent()
        }
        composable(route = Screen.DownloadVideo.route) {
            downloadVideoScreenContent()
        }
        composable(route = Screen.Language.route) {
            languageScreenContent()
        }
        composable(route = Screen.PrivacyPolicy.route) {
            privacyPolicyScreenContent()
        }
    }
}