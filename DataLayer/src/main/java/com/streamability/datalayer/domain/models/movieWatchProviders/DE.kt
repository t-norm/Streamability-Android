package com.streamability.datalayer.domain.models.movieWatchProviders

data class DE(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)