package com.example.datalayer.domain.models.movieDetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguage(
    val iso_639_1: String,
    val name: String
): Parcelable