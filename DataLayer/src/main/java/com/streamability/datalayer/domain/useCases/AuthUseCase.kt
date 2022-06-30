package com.streamability.datalayer.domain.useCases

import com.streamability.datalayer.domain.useCases.loginUseCases.*

data class AuthUseCase(
    val authUsernameUseCase: AuthUsernameUseCase,
    val authPasswordUseCase: AuthPasswordUseCase,
    val deleteDataStoreUseCase: DeleteDataStoreUseCase,
    val getDataStoreUseCase: GetDataStoreUseCase,
    val setDataStoreUseCase: SetDataStoreUseCase
    )