package com.streamability.datalayer.domain.models.movieWatchProviders

data class NL(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)