package com.streamability.datalayer.domain.models.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentSearches(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movieId: Int,
    val backdrop_path: String,
    val title: String,
    val overview: String,
)
