package com.miraelDev.vauma.presentation.mainScreen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.common.collect.ImmutableList
import com.miraelDev.vauma.exntensions.noRippleEffectClick
import com.miraelDev.vauma.navigation.NavigationState

@Composable
fun NavigationRailContent(
    navigationState: NavigationState,
) {

    val backStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

    val items =
        ImmutableList.of(
            NavigationItem.Home,
            NavigationItem.Search,
            NavigationItem.Library
        )

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(start = 4.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        items.forEach { item ->

            val selected = backStackEntry?.destination?.hierarchy?.any {
                it.route == item.screen.route
            } ?: false

            AddItem(
                item = item,
                navigationState = navigationState,
                selected = selected
            )
        }


    }
}

@Composable
private fun ColumnScope.AddItem(
    item: NavigationItem,
    navigationState: NavigationState,
    selected: Boolean
) {

    val background =
        if (selected) MaterialTheme.colors.primary else Color.Transparent

    val contentColor =
        if (selected) Color.White else MaterialTheme.colors.onBackground

    Column(
        modifier = Modifier
            .width(72.dp)
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(background)
                .noRippleEffectClick(
                    onClick = {
                        if (!selected) {
                            navigationState.navigateTo(item.screen.route)
                        }
                    })
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp),
                imageVector = item.icon,
                contentDescription = "icon",
                tint = contentColor
            )
        }
        Text(
            text = stringResource(id = item.title),
            color = MaterialTheme.colors.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}