package com.alecbrando.lib_data_layer.data.local.dataPref.dataprefint

import com.streamability.datalayer.domain.models.sharedPref.Login
import kotlinx.coroutines.flow.Flow

interface DataPreference {
    suspend fun setLoginPreference(username: String, password: String): Boolean
    suspend fun collectLoginPreference(): Flow<Login>

    suspend fun setDarKModePreference(darkMode: Boolean): Boolean
    suspend fun collectDarkModePreference(): Flow<Boolean>

    suspend fun removeUser()
}