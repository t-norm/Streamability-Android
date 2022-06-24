package com.streamability.datalayer.domain.useCases.loginUseCases

import com.streamability.datalayer.data.repo.Repository

class AuthPasswordUseCase(private val repo: Repository) {
    operator fun invoke(string: String): String{
            return repo.checkInputForm("password", string)
        }
    }