package com.miraeldev.detailinfo.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.miraeldev.anime.AnimeDetailInfo
import com.miraeldev.api.VaumaImageLoader
import com.miraeldev.detailinfo.R
import com.miraeldev.extensions.noRippleEffectClick
import com.miraeldev.theme.LightGreen

private const val RATING_SCREEN = 1
private const val DOWNLOAD_SCREEN = 2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    bottomSheetScreen: Int,
    animeDetailInfo: AnimeDetailInfo,
    imageLoader: VaumaImageLoader,
    onBackPressed: () -> Unit,
    modalSheetState: ModalBottomSheetState,
    onDownloadClick: (List<Int>) -> Unit,
    onCloseDownloadSheet: () -> Unit,
    bottomSheetContent: @Composable () -> Unit
) {

    BackHandler(modalSheetState.isVisible) {
        onBackPressed()
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        scrimColor = Color.Gray.copy(alpha = 0.5f),
        sheetContent = {

            when (bottomSheetScreen) {
                RATING_SCREEN -> {
                    RatingScreen(
                        animeDetailInfo = animeDetailInfo,
                        onDownloadClick = onDownloadClick,
                        onCloseDownloadSheet = onCloseDownloadSheet
                    )
                }

                DOWNLOAD_SCREEN -> {
                    DownloadScreen(
                        animeDetailInfo = animeDetailInfo,
                        imageLoader = imageLoader,
                        onDownloadClick = onDownloadClick,
                        onCloseDownloadSheet = onCloseDownloadSheet
                    )
                }
            }
        }
    ) {
        bottomSheetContent()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Suppress("UnusedPrivateMember")
@Composable
private fun RatingScreen(
    animeDetailInfo: AnimeDetailInfo,
    onDownloadClick: (List<Int>) -> Unit,
    onCloseDownloadSheet: () -> Unit,
) {
    val p = animeDetailInfo
    val selectedList = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colors.onBackground.copy(0.3f),
            thickness = 3.dp
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(R.string.give_rating),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f)
            )
        }

        Column() {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "9.8",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = " /10",
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    FixRatingBar(rating = 3.2f, modifier = Modifier.height(16.dp))
                    Text(
                        text = "(243455 users)",
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {

                    repeat(5) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "${5 - it}", color = MaterialTheme.colors.onBackground)
                            Box() {
                                Spacer(
                                    modifier = Modifier
                                        .height(6.dp)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(24.dp))
                                        .background(MaterialTheme.colors.onBackground.copy(0.2f))
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(6.dp)
                                        .fillMaxWidth(0.8f)
                                        .clip(RoundedCornerShape(24.dp))
                                        .background(MaterialTheme.colors.primary)
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f)
            )
        }

        UserRatingBar(rating = 5)

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCloseDownloadSheet,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightGreen.copy(0.8f),
                    contentColor = MaterialTheme.colors.primary
                )
            ) {
                Text(stringResource(R.string.close), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    onDownloadClick(selectedList)
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.submit), fontSize = 18.sp)
            }
        }
    }
}

@Composable
private fun DownloadScreen(
    animeDetailInfo: AnimeDetailInfo,
    imageLoader: VaumaImageLoader,
    onDownloadClick: (List<Int>) -> Unit,
    onCloseDownloadSheet: () -> Unit,
) {

    val selectedList = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .clip(RoundedCornerShape(24.dp)),
            color = MaterialTheme.colors.onBackground.copy(0.3f),
            thickness = 3.dp
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(R.string.download_episode),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                thickness = 2.dp,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
        }

        LazyRow(
            modifier = Modifier.clip(RoundedCornerShape(24.dp)),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(count = animeDetailInfo.videos.size, key = { it }) {
                EpisodeItem(
                    url = animeDetailInfo.videos[it].videoUrl480,
                    imageLoader = imageLoader,
                    videoName = animeDetailInfo.videos[it].videoName,
                    selected = selectedList.contains(it),
                    onEpisodeItemClick = {
                        if (selectedList.toList().contains(it)) {
                            selectedList.remove(it)
                        } else {
                            selectedList.add(it)
                        }
                    }
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCloseDownloadSheet,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LightGreen.copy(0.8f),
                    contentColor = MaterialTheme.colors.primary
                )
            ) {
                Text(stringResource(R.string.close), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    onDownloadClick(selectedList)
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.download), fontSize = 18.sp)
            }
        }
    }
}

@Composable
private fun EpisodeItem(
    url: String,
    imageLoader: VaumaImageLoader,
    videoName: String,
    selected: Boolean,
    onEpisodeItemClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .noRippleEffectClick { onEpisodeItemClick() }
            .padding(4.dp)
            .border(
                width = 3.dp,
                color = if (selected) MaterialTheme.colors.primary else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
    ) {
        AsyncImage(
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = imageLoader.load { data(url) },
            contentDescription = stringResource(R.string.anime_episode_preview),
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp),
            text = videoName,
            color = Color.White
        )
    }
}