package com.streamability.datalayer.domain.useCases.dataStoreUseCase

import com.streamability.datalayer.data.repo.Repository
import com.streamability.datalayer.domain.models.sharedPref.Login
import kotlinx.coroutines.flow.Flow

class GetDarkModeUseCase (private val repo: Repository) {
    suspend operator fun invoke(): Flow<Boolean> {
        return repo.getDarkModeLoginDataStore()
    }
}