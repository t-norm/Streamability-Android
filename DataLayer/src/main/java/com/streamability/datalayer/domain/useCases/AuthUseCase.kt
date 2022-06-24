package com.streamability.datalayer.domain.useCases

import com.streamability.datalayer.domain.useCases.loginUseCases.AuthPasswordUseCase
import com.streamability.datalayer.domain.useCases.loginUseCases.AuthUsernameUseCase

data class AuthUseCase(
    val authUsernameUseCase: AuthUsernameUseCase,
    val authPasswordUseCase: AuthPasswordUseCase,
    )