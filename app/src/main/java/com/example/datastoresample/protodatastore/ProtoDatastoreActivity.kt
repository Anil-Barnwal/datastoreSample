package com.example.datastoresample.protodatastore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModelProvider
import com.example.datastoresample.ProfilePreferences
import com.example.datastoresample.R
import com.example.datastoresample.protodatastore.data.UserPreferencesSerializer
import com.example.datastoresample.protodatastore.repository.UserPreferencesRepository
import com.example.datastoresample.protodatastore.viewmodel.LoginViewModel
import com.example.datastoresample.protodatastore.viewmodel.ViewModelFactory

private const val DATA_STORE_FILE_NAME = "user_prefs.pb"

val Context.userPreferencesStore: DataStore<ProfilePreferences> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = UserPreferencesSerializer
)

//val Context.paymentPreferencesStore: DataStore<PaymentPreferences> by dataStore(
//    fileName = DATA_STORE_FILE_NAME,
//    serializer = PaymentPreferencesSerializer
//)

class ProtoDatastoreActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var tvName: TextView
    lateinit var tvAge: TextView
    lateinit var saveButton: Button

    private lateinit var loginViewModel: LoginViewModel
//    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        tvName = findViewById(R.id.tv_name)
        tvAge = findViewById(R.id.tv_age)
        saveButton = findViewById(R.id.btn_save)

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreferencesRepository(userPreferencesStore))
        ).get(LoginViewModel::class.java)

//        paymentViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(PaymentPreferencesRepository(paymentPreferencesStore))
//        ).get(PaymentViewModel::class.java)

        // Get reference to our userManager class
        buttonSave()

        observeData()
    }

    private fun buttonSave() {
        // Gets the user input and saves it
        saveButton.setOnClickListener {
            loginViewModel.savePreferences(etName.text.toString(), etAge.text.toString().toInt())
//            paymentViewModel.savePreferences(12344, "Dec 07, 2021")


        }
    }

    private fun observeData() {
        loginViewModel.userPrefsLiveData.observe(this, { profilePrefs ->
            tvName.text = profilePrefs.userName
            tvAge.text = profilePrefs.age.toString()
        })

//        paymentViewModel.paymentPrefsLiveData.observe(this, { paymentPrefs ->
//            tvName.text = " ${paymentPrefs.paymentId}"
//            tvAge.text = paymentPrefs.date
//        })
    }
}