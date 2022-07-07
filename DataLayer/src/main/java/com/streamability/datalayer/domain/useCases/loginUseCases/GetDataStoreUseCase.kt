package com.streamability.datalayer.domain.useCases.loginUseCases

import com.streamability.datalayer.data.repo.Repository
import com.streamability.datalayer.domain.models.sharedPref.Login
import kotlinx.coroutines.flow.Flow

class GetDataStoreUseCase (private val repo: Repository) {
    suspend operator fun invoke(): Flow<Login> {
        return repo.getLoginDataStore()
    }
}