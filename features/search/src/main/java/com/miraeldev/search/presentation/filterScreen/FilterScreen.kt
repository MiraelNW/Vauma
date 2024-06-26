package com.miraeldev.search.presentation.filterScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miraeldev.designsystem.Toolbar
import com.miraeldev.extensions.pressClickEffect
import com.miraeldev.search.R
import com.miraeldev.search.presentation.filterScreen.filterComponent.FilterComponent
import com.miraeldev.theme.LightGreen700
import com.miraeldev.theme.LocalTheme
import kotlinx.collections.immutable.persistentListOf

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(component: FilterComponent) {

    val model by component.model.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            Toolbar(
                onBackPressed = component::onBackPressed,
                text = R.string.filter
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {

                Text(
                    modifier = Modifier.padding(start = 4.dp, bottom = 16.dp),
                    text = stringResource(R.string.year),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontFamily = FontFamily.SansSerif
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    val filterCategoriesYearList = persistentListOf(
                        stringResource(R.string.ongoing),
                        "2023",
                        "2022",
                        "2021",
                        "2015-2020",
                        "2008-2014",
                        "2000-2007",
                        stringResource(R.string.before_2000),
                    )

                    filterCategoriesYearList.forEach { category ->
                        CategoryField(category, category == model.yearCategory) {
                            component.selectCategory(
                                YEAR_CATEGORIES_ID,
                                category,
                                category == model.yearCategory
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier.padding(start = 4.dp, bottom = 16.dp),
                    text = stringResource(R.string.genre),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontFamily = FontFamily.SansSerif
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    model.genreList.forEachIndexed { index, category ->
                        CategoryField(category.name, category.isSelected) {
                            component.selectCategory(
                                index + 4,
                                category.name,
                                category.isSelected
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier.padding(start = 4.dp, bottom = 16.dp),
                    text = stringResource(R.string.sortBy),
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontFamily = FontFamily.SansSerif
                )

                FlowRow(
                    modifier = Modifier.padding(bottom = 48.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    val filterCategoriesSortList = persistentListOf(
                        stringResource(R.string.sort_by_name),
                        stringResource(R.string.sort_by_rate),
                        stringResource(R.string.sort_by_episode_count),
                        stringResource(R.string.sort_by_year_released),
                    )

                    filterCategoriesSortList.forEach { category ->
                        CategoryField(category, category == model.sortByCategory) {
                            component.selectCategory(
                                SORT_CATEGORIES_ID,
                                category,
                                category == model.sortByCategory
                            )
                        }
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 12.dp),
                backgroundColor = MaterialTheme.colors.background,
                onClick = component::clearAllFilter
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colors.primary,
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_bin),
                        contentDescription = stringResource(R.string.clear)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.clear),
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryField(
    text: String,
    selected: Boolean,
    onCategoryClick: () -> Unit
) {

    OutlinedButton(
        modifier = Modifier.pressClickEffect(),
        onClick = onCategoryClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = if (selected) MaterialTheme.colors.primary
                else MaterialTheme.colors.background
            ),
        elevation = ButtonDefaults.elevation(3.dp),
        border = BorderStroke(
            1.dp, color = MaterialTheme.colors.primary.copy(alpha = 0.9f)
        ),
    ) {
        Text(
            text = text,
            color = if (selected) Color.White
            else if (LocalTheme.current) LightGreen700
            else MaterialTheme.colors.primary,
            maxLines = 1,
            fontSize = 16.sp
        )
    }
}

private const val YEAR_CATEGORIES_ID = 1
private const val SORT_CATEGORIES_ID = 2