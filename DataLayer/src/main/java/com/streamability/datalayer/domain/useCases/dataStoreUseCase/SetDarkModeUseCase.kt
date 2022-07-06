package com.streamability.datalayer.domain.useCases.dataStoreUseCase

import com.streamability.datalayer.data.repo.Repository

class SetDarkModeUseCase (private val repo: Repository) {
    suspend operator fun invoke(darkMode: Boolean): Boolean{
        return repo.setDarkModeDataStore(darkMode)
    }
}