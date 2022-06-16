package com.streamability.datalayer.domain.models.movieWatchProviders

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieWatchProvidersModel(
    val id: Int,
    val results: Results
): Parcelable