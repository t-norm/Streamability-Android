package com.streamability.datalayer.domain.useCases.movieUseCases

import com.streamability.datalayer.data.repo.Repository
import com.streamability.datalayer.domain.models.searchMovie.SearchMovieModel
import com.streamability.datalayer.utils.Resource

/***
use case to call repo. Use case will interact with View model
 */

class SearchMovieUseCase(private val repo: Repository) {
    suspend operator fun invoke(apiKey: String, query: String): Resource<SearchMovieModel?> {
        return repo.getMovieSearch(apiKey, query)
    }
}