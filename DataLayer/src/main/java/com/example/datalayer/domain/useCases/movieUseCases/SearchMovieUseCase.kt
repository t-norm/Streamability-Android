package com.example.datalayer.domain.useCases.movieUseCases

import com.example.datalayer.data.repo.Repository
import com.example.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.example.datalayer.domain.models.searchMovie.SearchMovieModel
import com.example.datalayer.utils.Resource

class SearchMovieUseCase(private val repo: Repository) {
    suspend operator fun invoke(apiKey: String): Resource<SearchMovieModel> {
        return repo.getMovieSearch()
    }
}