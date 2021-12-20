package com.example.datastoresample.prefsdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map

class UserPrefsHelper(private val context: Context) {

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")

        // At the top level of your kotlin file:
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    // Store user data
    suspend fun storeUser(age: Int, name: String) {
        context.dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_NAME_KEY] = name
        }
    }


    //Retrieving saved items
    val ageFlow: LiveData<Int>
    get() = context.dataStore.data.map { prefs ->
        prefs[USER_AGE_KEY] ?: 0
    }.asLiveData()

    val nameFlow : LiveData<String>
    get() = context.dataStore.data.map { prefs ->
        prefs[USER_NAME_KEY] ?: ""
    }.asLiveData()
}
