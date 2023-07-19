package com.miraelDev.anix.presentation.AnimeListScreen.SettingsScreen.ColorPalette

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.miraelDev.anix.R
import com.miraelDev.anix.domain.models.AnimeInfo
import com.miraelDev.anix.ui.theme.Gold

@Composable
fun AnimeList() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AnimeListPreviewToolbar()

        CustomTabRow()

        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(8) {
                AnimeCard()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimeCard() {

    val item = AnimeInfo(1)

    Card(
        onClick = {},
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row() {
            AsyncImage(
                model = item.image,
                contentDescription = item.nameEn,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(100.dp)
                    .width(50.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(
                modifier = Modifier
                    .width(2.dp)
            )
            AnimePreview(animeItem = item)


        }
    }
}

@Composable
private fun AnimePreview(animeItem: AnimeInfo) {
    Column(
        Modifier.padding(top = 4.dp, end = 8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = animeItem.nameEn,
                color = MaterialTheme.colors.onBackground,
                fontSize = 8.sp
            )
            Rating(animeItem = animeItem)
        }
        Text(
            text = animeItem.nameRu,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
            fontSize = 8.sp
        )
        Spacer(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
        )
        Text(
            text = animeItem.description,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground,
            fontSize = 8.sp
        )
    }
}

@Composable
private fun Rating(animeItem: AnimeInfo) {
    Row {
        Icon(
            modifier = Modifier.size(8.dp),
            imageVector = Icons.Filled.Star,
            contentDescription = "Rating",
            tint = Gold
        )
        Text(
            text = animeItem.score.toString(),
            color = MaterialTheme.colors.onBackground,
            fontSize = 8.sp
        )
    }
}

@Composable
private fun CustomTabRow() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val categoryList = listOf(
            stringResource(R.string.new_str),
            stringResource(R.string.popular),
            stringResource(R.string.name),
            stringResource(R.string.films)
        )

        categoryList.forEachIndexed { id, text ->
            CustomTab(id, text)
        }
    }
}

@Composable
private fun CustomTab(
    id: Int,
    text: String
) {
    if (id != 0) {
        Text(text = text)
    } else {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary)
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = text,

                )
        }
    }
    Spacer(modifier = Modifier.width(12.dp))

}

@Composable
private fun AnimeListPreviewToolbar(
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "AniX", color = MaterialTheme.colors.onBackground, fontSize = 14.sp)

            SettingsAndThemeButtons()

        }
    }
}

@Composable
private fun SettingsAndThemeButtons() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        val isDarkTheme = isSystemInDarkTheme()
        Icon(
            modifier = Modifier
                .size(18.dp)
                .padding(2.dp),
            painter = painterResource(
                id = if (isDarkTheme) R.drawable.ic_sun else R.drawable.ic_moon
            ),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "Change theme"
        )
        Spacer(
            modifier = Modifier
                .height(4.dp)
                .width(2.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_settings),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = "Settings",
            modifier = Modifier
                .size(18.dp)
                .padding(2.dp)
        )
    }
}