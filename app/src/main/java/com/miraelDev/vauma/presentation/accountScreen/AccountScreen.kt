package com.miraelDev.vauma.presentation.accountScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.miraelDev.vauma.R
import com.miraelDev.vauma.presentation.accountScreen.settings.notificationsScreen.Switcher
import com.miraelDev.vauma.presentation.mainScreen.LocalTheme

@Composable
fun AccountScreen(
    onSettingItemClick: (Int) -> Unit,
    onDarkThemeClick: () -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Toolbar()
        ProfileNameAndImage()
        AllSettings(
            onSettingItemClick = onSettingItemClick,
            onDarkThemeClick = onDarkThemeClick
        )
    }
}

@Composable
private fun Toolbar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary.copy(alpha = 0.1f))
                        .size(32.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_brand_icon),
                    contentDescription = "brand icon",
                    tint = MaterialTheme.colors.primary
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .width(8.dp)
                )
                Text(
                    text = stringResource(R.string.profile),
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontFamily = FontFamily.SansSerif,
                )
            }

        }
    }
}

@Composable
private fun ProfileNameAndImage() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape),
            model = "https://gravatar.com/avatar/0143887282216779617c58f10181af2e?s=400&d=robohash&r=x",
            placeholder = painterResource(id = R.drawable.ic_account),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile image"
        )
        Column {
            Text(text = "Nick name")
            Text(text = "goodworkinspclass@mail.ru")
        }
    }
}

@Composable
private fun AllSettings(
    onSettingItemClick: (Int) -> Unit,
    onDarkThemeClick: () -> Unit,
) {

    val settingsItems = listOf(
        stringResource(R.string.edit_profile) to R.drawable.ic_edit_profile,
        stringResource(R.string.notification) to R.drawable.ic_notification,
        stringResource(R.string.download_video) to R.drawable.ic_download_videos,
        )

    Column(
        modifier = Modifier.padding(bottom = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        settingsItems.forEachIndexed { index, textWithIconId ->
            SettingItem(
                index = index,
                text = textWithIconId.first,
                icon = textWithIconId.second,
                onSettingItemClick = onSettingItemClick
            )
        }

        DarkTheme(onDarkThemeClick = onDarkThemeClick)

        PrivacyPolicyItem(onPrivacyPolicyItemClick = onSettingItemClick)

        LogOut(onLogOutClick = {})
    }
}

@Composable
private fun SettingItem(
    index: Int,
    text: String,
    icon: Int,
    onSettingItemClick: (Int) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onSettingItemClick(index) }
            .padding(8.dp)
            .border(0.dp, MaterialTheme.colors.background)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = text,
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                color = MaterialTheme.colors.onBackground,
                fontSize = 18.sp
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_forward),
            contentDescription = stringResource(R.string.show),
            tint = MaterialTheme.colors.onBackground
        )

    }
}

@Composable
private fun PrivacyPolicyItem(
    onPrivacyPolicyItemClick: (Int) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onPrivacyPolicyItemClick(4) }
            .padding(8.dp)
            .border(0.dp, MaterialTheme.colors.background)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_privacy_policy),
                contentDescription = stringResource(id = R.string.privacy_policy),
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(id = R.string.privacy_policy),
                color = MaterialTheme.colors.onBackground,
                fontSize = 18.sp
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_forward),
            contentDescription = stringResource(R.string.show),
            tint = MaterialTheme.colors.onBackground
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun DarkTheme(
    onDarkThemeClick: () -> Unit
) {

    val isDarkTheme = LocalTheme.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onDarkThemeClick() }
            .padding(8.dp)
            .border(0.dp, MaterialTheme.colors.background)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedContent(
                targetState = isDarkTheme,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150)) +
                            scaleIn(
                                initialScale = 0.4f,
                                animationSpec = tween(300)
                            ) with
                            fadeOut(animationSpec = tween(150))
                }, label = ""
            ) { darkTheme ->

                val iconId = if (darkTheme) {
                    R.drawable.ic_sun
                } else {
                    R.drawable.ic_moon
                }
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(iconId),
                    tint = MaterialTheme.colors.primary,
                    contentDescription = "Change theme"
                )

            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.dark_theme),
                color = MaterialTheme.colors.onBackground,
                fontSize = 18.sp
            )
        }

        Switcher(isSelected = isDarkTheme)

    }
}

@Composable
private fun LogOut(
    onLogOutClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onLogOutClick() }
            .padding(8.dp)
            .border(0.dp, MaterialTheme.colors.background)
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_log_out),
                contentDescription = stringResource(R.string.LogOut),
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.LogOut),
                color = Color.Red,
                fontSize = 18.sp
            )
        }
    }
}


