package com.streamability.datalayer.domain.models.sharedPref

data class Login(
    val token: String,
    val username: String,
    val password: String
)
