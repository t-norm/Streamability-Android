package com.example.datalayer.domain.useCases.movieUseCases

import com.example.datalayer.data.repo.Repository
import com.example.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.example.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.example.datalayer.utils.Resource

class WatchProvidersUseCase(private val repo: Repository) {
    suspend operator fun invoke(): Resource<MovieWatchProvidersModel> {
        return repo.getWatchProviders()
    }
}