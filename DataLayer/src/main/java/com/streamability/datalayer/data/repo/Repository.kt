package com.streamability.datalayer.data.repo

import com.alecbrando.lib_data_layer.data.local.dataPref.dataprefint.DataPreference
import com.streamability.datalayer.domain.dataInterfaces.RemoteDataSource
import com.streamability.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.streamability.datalayer.domain.models.movieWatchProviders.MovieWatchProvidersModel
import com.streamability.datalayer.domain.models.searchMovie.Result
import com.streamability.datalayer.domain.models.searchMovie.SearchMovieModel
import com.streamability.datalayer.domain.models.sharedPref.Login
import com.streamability.datalayer.utils.Resource
import com.streamability.datalayer.utils.Validation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
/***
Repo where we will handle api and local room calls. injecting Remote and Local
implementations so we can access the calls
 */
class Repository @Inject constructor(
    private val local: DataPreference,
    private val remote: RemoteDataSource,
    private val validate: Validation
    ) {

    suspend fun getMovieSearch(apiKey: String, query: String): Resource<SearchMovieModel?> {
        val movieSearch = remote.searchMovie(apiKey, query)

        return if (movieSearch.isSuccessful) {
            val search = movieSearch.body()
            Resource.Success(search)
        } else {
            Resource.Error("API call failed")
        }
    }

    suspend fun getMovieDetails(movieId: Int, apiKey: String): Resource<MovieDetailsModel?> {
        val movieDetails = remote.movieDetails(movieId, apiKey)

        return if (movieDetails.isSuccessful) {
            val details = movieDetails.body()
            Resource.Success(details)
        } else {
            Resource.Error("API call failed")
        }
    }

    suspend fun getWatchProviders(movieId: Int, apiKey: String): Resource<MovieWatchProvidersModel?> {
        val watchProviders = remote.watchProviders(movieId, apiKey)

        return if (watchProviders.isSuccessful) {
            val providers = watchProviders.body()
            Resource.Success(providers)
        } else {
            Resource.Error("API call failed")
        }
    }

    fun checkInputForm(inputForm: String, string: String): String{
        return validate.validateForm(inputForm, string)
    }

    suspend fun setDataStore(username: String, password: String): Boolean {
        return local.setPreference(username, password)
    }
    suspend fun getDataStore(): Flow<Login> {
        return local.collectPreference()
    }
    suspend fun deleteDataStore() {
        local.removeUser()
    }
}