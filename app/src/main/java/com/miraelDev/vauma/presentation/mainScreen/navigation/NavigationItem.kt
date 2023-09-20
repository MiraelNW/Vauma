package com.miraelDev.vauma.presentation.mainScreen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.miraelDev.vauma.R
import com.miraelDev.vauma.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val title: Int,
    val icon: Int
) {
    object Home : NavigationItem(
        screen = Screen.Home,
        title = R.string.home,
        icon = R.drawable.ic_home
    )

    object Search : NavigationItem(
        screen = Screen.SearchAndFilter,
        title = R.string.search,
        icon = R.drawable.ic_search
    )

    object Favourite : NavigationItem(
        screen = Screen.Favourite,
        title = R.string.favourite,
        icon =  R.drawable.ic_heart
    )

    object Account : NavigationItem(
        screen = Screen.Account,
        title = R.string.account,
        icon = R.drawable.ic_account
    )
}
