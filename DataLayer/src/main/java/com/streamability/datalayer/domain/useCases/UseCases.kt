package com.streamability.datalayer.domain.useCases

import com.streamability.datalayer.domain.useCases.movieUseCases.MovieDetailsUseCase
import com.streamability.datalayer.domain.useCases.movieUseCases.SearchMovieUseCase
import com.streamability.datalayer.domain.useCases.movieUseCases.WatchProvidersUseCase

/***
Data Class to wrap all of our use cases. Will need to break
this apart if we add more search capabilities like looking for tv shows
 */

data class UseCases(
    val movieDetailsUseCase: MovieDetailsUseCase,
    val searchMovieUseCase: SearchMovieUseCase,
    val watchProvidersUseCase: WatchProvidersUseCase
)
