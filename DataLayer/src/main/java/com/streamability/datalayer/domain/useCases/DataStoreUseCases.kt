package com.streamability.datalayer.domain.useCases

import com.streamability.datalayer.domain.useCases.dataStoreUseCase.GetDarkModeUseCase
import com.streamability.datalayer.domain.useCases.dataStoreUseCase.SetDarkModeUseCase

data class DataStoreUseCases(
    val getDarkModeUseCase: GetDarkModeUseCase,
    val setDarkModeUseCase: SetDarkModeUseCase,
    )