package com.streamability.datalayer.domain.models.searchMovie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchMovieModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
): Parcelable