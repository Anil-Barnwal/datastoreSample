package com.example.datastoresample.protodatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datastoresample.protodatastore.repository.UserPreferencesRepository

class ViewModelFactory(private val repository: UserPreferencesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }

//        if (modelClass.isAssignableFrom(PaymentViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return PaymentViewModel(repository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}