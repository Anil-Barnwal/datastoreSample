package com.example.datastoresample.prefsdatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.datastoresample.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrefsDatastoreActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var tvName: TextView
    lateinit var tvAge: TextView
    lateinit var saveButton: Button

    lateinit var userPrefsHelper: UserPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        tvName = findViewById(R.id.tv_name)
        tvAge = findViewById(R.id.tv_age)
        saveButton = findViewById(R.id.btn_save)

        // Get reference to our userManager class
        userPrefsHelper = UserPrefsHelper(this)

        buttonSave()

        observeData()
    }

    private fun buttonSave() {
        // Gets the user input and saves it
        saveButton.setOnClickListener {
            // Stores the values
            GlobalScope.launch {
                userPrefsHelper.storeUser(etAge.text.toString().toInt(), etName.text.toString())
            }
        }
    }

    private fun observeData() {
        // Updates age
        this.userPrefsHelper.ageFlow.observe(this) {
            tvAge.text = it.toString()
        }

        // Updates name
        userPrefsHelper.nameFlow.observe(this) {
            tvName.text = it.toString()
        }
    }
}