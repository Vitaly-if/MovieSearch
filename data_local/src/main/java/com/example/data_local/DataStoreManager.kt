package com.example.data_local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


class DataStoreManager(private val context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun saveMyObject(moviesDto: MoviesDto) {
        val jsonString = Json.encodeToString(moviesDto)
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("object_movies")] = jsonString
        }
    }

    val moviesDtoFlow: Flow<MoviesDto?> = dataStore.data
        .map { preferences ->

            preferences[stringPreferencesKey("object_movies")]?.let {
                Json.decodeFromString<MoviesDto>(
                    it
                )
            }
        }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_cache")