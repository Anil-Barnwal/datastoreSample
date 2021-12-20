package com.example.datastoresample.protodatastore.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.datastoresample.PaymentPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class PaymentPreferencesRepository(private val preferencesStore: DataStore<PaymentPreferences>) {

    val TAG = PaymentPreferencesRepository::class.java.simpleName

    val paymentPreferencesFlow: Flow<PaymentPreferences> = preferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(PaymentPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updatePaymentId(paymentId: Long){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().setPaymentId(paymentId).build()
        }
    }

    suspend fun updateDate(date:String){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().setDate(date).build()
        }
    }

    suspend fun clearPaymentId(){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().clearPaymentId().build()
        }
    }

    suspend fun clearDate(){
        preferencesStore.updateData {prefs ->
            prefs.toBuilder().clearDate().build()
        }
    }

    suspend fun clearPrefsDataStore(){
        preferencesStore.updateData { prefs ->
            prefs.toBuilder().clear().build()
        }
    }
}