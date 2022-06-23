package com.alecbrando.lib_data_layer.data.local.dataPref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.alecbrando.lib_data_layer.data.local.dataPref.dataprefint.DataPreference
import com.streamability.datalayer.domain.models.sharedPref.Login
import kotlinx.coroutines.flow.*
import java.lang.Exception

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

class DataPrefImpl(private val context: Context): DataPreference {

    override suspend fun setPreference(value: String) {
        val preferenceKey = stringPreferencesKey("user")
        context.dataStore.edit {it[preferenceKey] = value}
    }

    override suspend fun collectPreference(): Flow<Login> {
        val dataFlow: Flow<Login> = context.dataStore.data.map { preference ->
            val token = preference[stringPreferencesKey("user")] ?: ""
            val username = preference[stringPreferencesKey("username")] ?: ""
            val password = preference[stringPreferencesKey("password")] ?: ""
            Login(token, username, password)
        }
        return dataFlow
    }

    override suspend fun removeUser(key: String) {
       try {
           val preferenceKey = stringPreferencesKey(key)
           context.dataStore.edit {
               it.remove(preferenceKey)
           }
       }catch (e: Exception){
           e.localizedMessage
       }
    }
}