package com.example.datalayer.domain.models.movieWatchProviders

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class US(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
): Parcelable