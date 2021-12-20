package com.example.datastoresample.protodatastore.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.datastoresample.ProfilePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class UserPreferencesRepository(private val preferencesStore: DataStore<ProfilePreferences>) {

    val TAG = UserPreferencesRepository::class.java.simpleName

    val userPreferencesFlow: Flow<ProfilePreferences> = preferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(ProfilePreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateUserName(username: String){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().setUserName(username).build()
        }
    }

    suspend fun updateAge(age:Int){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().setAge(age).build()
        }
    }

    suspend fun clearUserName(){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().clearUserName().build()
        }
    }

    suspend fun clearAge(){
        preferencesStore.updateData {prefs ->
            prefs.toBuilder().clearAge().build()
        }
    }

    suspend fun clearPrefsDataStore(){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().clear().build()
        }
    }
}