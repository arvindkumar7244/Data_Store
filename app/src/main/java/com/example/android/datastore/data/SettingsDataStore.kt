package com.example.android.datastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


private const val MY_PREFERENCE = "my_preference"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MY_PREFERENCE)

class SettingsDatastore(private val context: Context) {

    private val sortByNamePreferenceKey = booleanPreferencesKey("sort_by_name")
    private val sortByPriorityPreferenceKey = booleanPreferencesKey("sort_by_priority")
    private val showCompletedPreferenceKey = booleanPreferencesKey("show_completed")

    val sortByNameFlow: Flow<Boolean> = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[sortByNamePreferenceKey] ?: false
    }

    suspend fun saveSortByNameToPref(sortByName: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[sortByNamePreferenceKey] = sortByName
        }
    }

    val sortByPriorityFlow: Flow<Boolean> = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[sortByPriorityPreferenceKey] ?: false
    }

    suspend fun saveSortByPriorityToPref(sortByPriority: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[sortByPriorityPreferenceKey] = sortByPriority
        }
    }

    val showCompletedFlow: Flow<Boolean> = context.dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[showCompletedPreferenceKey] ?: false
    }

    suspend fun saveShowCompletedToPref(showCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[showCompletedPreferenceKey] = showCompleted
        }
    }
}