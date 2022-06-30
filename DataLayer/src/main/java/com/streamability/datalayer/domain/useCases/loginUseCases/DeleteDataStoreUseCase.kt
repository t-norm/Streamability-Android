package com.streamability.datalayer.domain.useCases.loginUseCases

import com.streamability.datalayer.data.repo.Repository

class DeleteDataStoreUseCase (private val repo: Repository) {
    suspend operator fun invoke(){
        return repo.deleteDataStore()
    }
}