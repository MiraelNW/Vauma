package com.miraeldev.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
internal data class SearchHistoryDbModel(
    @PrimaryKey val searchHistoryItem : String
)