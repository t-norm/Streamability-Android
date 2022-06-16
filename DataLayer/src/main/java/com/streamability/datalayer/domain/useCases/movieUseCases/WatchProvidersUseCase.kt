package com.streamability.datalayer.domain.useCases.movieUseCases

import com.streamability.datalayer.data.repo.Repository
import com.streamability.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.streamability.datalayer.utils.Resource

/***
use case to call repo. Use case will interact with View model
 */

class WatchProvidersUseCase(private val repo: Repository) {
    suspend operator fun invoke(movieId: Int, apiKey:String): Resource<MovieWatchProvidersModel?> {
        return repo.getWatchProviders(movieId, apiKey)
    }
}