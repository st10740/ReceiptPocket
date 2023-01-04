package com.example.receiptpocket.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.receiptpocket.R

class RegisterActivity : AppCompatActivity() {
    private lateinit var backBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Back Button
        backBtn = findViewById(R.id.back_btn)
        backBtn.setOnClickListener { finish() } // 需再改
    }
}