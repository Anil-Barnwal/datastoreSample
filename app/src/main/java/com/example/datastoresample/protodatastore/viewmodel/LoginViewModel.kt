package com.example.datastoresample.protodatastore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.datastoresample.protodatastore.repository.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val profilePrefsRepository: UserPreferencesRepository): ViewModel() {

    val userPrefsLiveData = profilePrefsRepository.userPreferencesFlow.asLiveData()

    fun savePreferences(userName: String, age:Int){
        viewModelScope.launch(Dispatchers.IO) {
            profilePrefsRepository.updateUserName(userName)
            profilePrefsRepository.updateAge(age)
        }
    }

    fun clearProfilePrefs(){
        viewModelScope.launch(Dispatchers.IO) {
            profilePrefsRepository.clearPrefsDataStore()
        }
    }

    fun clearUserName(){
        viewModelScope.launch(Dispatchers.IO) {
            profilePrefsRepository.clearUserName()
        }
    }

    fun clearAge(){
        viewModelScope.launch(Dispatchers.IO) {
            profilePrefsRepository.clearAge()
        }
    }
}