package com.example.receiptpocket.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.receiptpocket.R
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Register button
        registerBtn = findViewById(R.id.register_btn)
        registerBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Login button
        loginBtn = findViewById(R.id.login_btn)
        loginBtn.setOnClickListener {
            startActivity(Intent(this, PocketActivity::class.java))
        }

    }
}