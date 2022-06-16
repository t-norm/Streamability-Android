package com.streamability.datalayer.domain.useCases.movieUseCases

import com.streamability.datalayer.data.repo.Repository
import com.streamability.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.streamability.datalayer.utils.Resource

/***
use case to call repo. Use case will interact with View model
 */

class MovieDetailsUseCase(private val repo: Repository) {
    suspend operator fun invoke(movieId: Int, apiKey:String): Resource<MovieDetailsModel?> {
        return repo.getMovieDetails(movieId, apiKey)
    }
}