package com.example.datalayer.domain.models.searchMovie

import android.os.Parcelable
import com.example.datalayer.domain.models.searchMovie.Result
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchMovieModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
): Parcelable