package com.example.datastoresample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.datastoresample.prefsdatastore.PrefsDatastoreActivity
import com.example.datastoresample.protodatastore.ProtoDatastoreActivity

class DashboardActivity:Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        findViewById<Button>(R.id.prefs_ds_btn).setOnClickListener {
            startActivity(Intent(this, PrefsDatastoreActivity::class.java))
        }

        findViewById<Button>(R.id.proto_ds_btn).setOnClickListener {
            startActivity(Intent(this, ProtoDatastoreActivity::class.java))
        }
    }
}