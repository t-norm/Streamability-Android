package com.example.datalayer.domain.models.movieWatchProviders

data class GR(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)