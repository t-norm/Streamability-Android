package com.alecbrando.lib_data_layer.data.local.dataPref

import android.content.Context
import android.util.Log
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

    override suspend fun setPreference(username: String, password: String): Boolean {
        val preferenceKey = stringPreferencesKey("username")
        val preferenceKey2 = stringPreferencesKey("password")
        context.dataStore.edit {it[preferenceKey] = username}
        context.dataStore.edit {it[preferenceKey2] = password}
        return true
    }

    override suspend fun collectPreference(): Flow<Login> {
        val dataFlow: Flow<Login> = context.dataStore.data.map { preference ->
            val username = preference[stringPreferencesKey("username")] ?: ""
            val password = preference[stringPreferencesKey("password")] ?: ""
            Login(username, password)
        }
        return dataFlow
    }

    override suspend fun removeUser() {
       try {
           val preferenceKey = stringPreferencesKey("username")
           val preferenceKey2 = stringPreferencesKey("password")
           context.dataStore.edit {
               it.remove(preferenceKey)
               it.remove(preferenceKey2)
           }
           Log.d("RemoveDataStore", "UserRemoved")
       }catch (e: Exception){
           e.localizedMessage
       }
    }
}