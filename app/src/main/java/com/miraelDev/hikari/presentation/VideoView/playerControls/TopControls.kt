package com.miraelDev.hikari.presentation.VideoView.playerControls

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.miraelDev.hikari.R
import com.miraelDev.hikari.ui.theme.DirtyWhite
import com.miraelDev.hikari.ui.theme.Green

@Composable
fun TopControl(
    modifier: Modifier = Modifier,
    title: () -> String,
    onBackIconClick: () -> Unit,
    onEpisodeIconClick: () -> Unit,
    onAutoLoadNextVideoClick: (Boolean) -> Unit,
) {
    val videoTitle = remember(title()) { title() }

    var autoLoadNextVideo by rememberSaveable { mutableStateOf(false) }
    val color = remember { Animatable(DirtyWhite) }

    LaunchedEffect(autoLoadNextVideo) {
        if (autoLoadNextVideo) {
            color.animateTo(Green, animationSpec = tween(1000))
        } else {
            color.animateTo(DirtyWhite, animationSpec = tween(1000))
        }
    }

    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onBackIconClick
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = DirtyWhite
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = videoTitle,
                style = MaterialTheme.typography.h6,
                color = DirtyWhite
            )
        }

        Row {
            IconButton(
                modifier = Modifier
                    .size(44.dp)
                    .padding(end = 16.dp),
                onClick = {
                    autoLoadNextVideo = !autoLoadNextVideo
                    onAutoLoadNextVideoClick(autoLoadNextVideo)
                }
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_auto_next_video),
                    contentDescription = "auto next video",
                    tint = color.value
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                modifier = Modifier
                    .size(44.dp)
                    .padding(end = 16.dp),
                onClick = onEpisodeIconClick
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_episodes),
                    contentDescription = "episodes",
                    tint = DirtyWhite
                )
            }
        }
    }
}