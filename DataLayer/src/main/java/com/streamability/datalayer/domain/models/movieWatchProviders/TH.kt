package com.streamability.datalayer.domain.models.movieWatchProviders

data class TH(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)