package com.example.datalayer.domain.useCases

import com.example.datalayer.domain.useCases.movieUseCases.MovieDetailsUseCase
import com.example.datalayer.domain.useCases.movieUseCases.SearchMovieUseCase
import com.example.datalayer.domain.useCases.movieUseCases.WatchProvidersUseCase

data class UseCases(
    val movieDetailsUseCase: MovieDetailsUseCase,
    val searchMovieUseCase: SearchMovieUseCase,
    val watchProvidersUseCase: WatchProvidersUseCase
)
