package com.alecbrando.lib_data_layer.data.local.dataPref.dataprefint

import com.streamability.datalayer.domain.models.sharedPref.Login
import kotlinx.coroutines.flow.Flow

interface DataPreference {
    suspend fun setPreference(username: String, password: String)
    suspend fun collectPreference(): Flow<Login>
    suspend fun removeUser()
}