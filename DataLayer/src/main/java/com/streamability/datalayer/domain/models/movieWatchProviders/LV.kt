package com.streamability.datalayer.domain.models.movieWatchProviders

data class LV(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String
)