package com.example.datastoresample.protodatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.datastoresample.protodatastore.repository.PaymentPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(private val paymentPrefsRepository: PaymentPreferencesRepository): ViewModel() {

    val paymentPrefsLiveData = paymentPrefsRepository.paymentPreferencesFlow.asLiveData()

    fun savePreferences(paymentId: Long, date:String){
        viewModelScope.launch(Dispatchers.IO) {
            paymentPrefsRepository.updatePaymentId(paymentId)
            paymentPrefsRepository.updateDate(date)
        }
    }

    fun clearProfilePrefs(){
        viewModelScope.launch(Dispatchers.IO) {
            paymentPrefsRepository.clearPrefsDataStore()
        }
    }

    fun clearPaymentId(){
        viewModelScope.launch(Dispatchers.IO) {
            paymentPrefsRepository.clearPaymentId()
        }
    }

    fun clearDate(){
        viewModelScope.launch(Dispatchers.IO) {
            paymentPrefsRepository.clearDate()
        }
    }
}