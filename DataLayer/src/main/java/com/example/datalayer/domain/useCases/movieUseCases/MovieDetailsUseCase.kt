package com.example.datalayer.domain.useCases.movieUseCases

import com.example.datalayer.data.repo.Repository
import com.example.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.example.datalayer.utils.Resource

class MovieDetailsUseCase(private val repo: Repository) {
    suspend operator fun invoke(): Resource<MovieDetailsModel> {
        return repo.getMovieDetails()
    }
}