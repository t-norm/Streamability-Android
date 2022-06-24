package com.streamability.datalayer.domain.useCases.loginUseCases

import com.streamability.datalayer.data.repo.Repository

class AuthUsernameUseCase(private val repo: Repository) {
    operator fun invoke(string: String): String{
            return repo.checkInputForm("username", string)
        }
    }