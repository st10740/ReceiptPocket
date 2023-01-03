package com.example.receiptpocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set ToolBar
        val toolbar = findViewById<Toolbar>(R.id.login_toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

    }
}