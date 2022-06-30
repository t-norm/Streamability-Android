package com.streamability.datalayer.domain.useCases.loginUseCases

import com.streamability.datalayer.data.repo.Repository

class SetDataStoreUseCase (private val repo: Repository) {
    suspend operator fun invoke(username: String, password: String){
        return repo.setDataStore(username, password)
    }
}