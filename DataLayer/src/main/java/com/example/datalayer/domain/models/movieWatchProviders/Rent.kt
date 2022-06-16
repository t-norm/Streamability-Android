package com.example.datalayer.domain.models.movieWatchProviders

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rent(
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
): Parcelable