package com.alecbrando.lib_data_layer.data.local.dataPref.dataprefint

import com.alecbrando.lib_data_layer.domain.models.DataStoreModel
import kotlinx.coroutines.flow.Flow

interface DataPreference {
    suspend fun setPreference(key: String, value: String)
    suspend fun collectPreference(): Flow<DataStoreModel>
    suspend fun removeUser(key: String)
}