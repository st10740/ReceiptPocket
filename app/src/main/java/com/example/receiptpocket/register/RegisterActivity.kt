package com.example.receiptpocket.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.receiptpocket.R
import com.example.receiptpocket.login.MainActivity
import com.example.receiptpocket.register.Presenters.RegisterPresenter
import com.example.receiptpocket.register.Presenters.RegisterPresenterImpl
import com.example.receiptpocket.register.Views.RegisterView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity(), RegisterView, View.OnClickListener {
    private lateinit var accTextInputLayout: TextInputLayout
    private lateinit var accTextInputEditText: TextInputEditText
    private lateinit var nameTextInputLayout: TextInputLayout
    private lateinit var nameTextInputEditText: TextInputEditText
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var passordTextInputEditText: TextInputEditText
    private lateinit var registerBtn: Button
    private lateinit var backBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bindingViews()

        registerBtn.setOnClickListener(this)
        backBtn.setOnClickListener(this)

    }

    private fun bindingViews(){
        accTextInputLayout = findViewById(R.id.acc_text_input_layout)
        accTextInputEditText = findViewById(R.id.acc_edit_text)
        nameTextInputLayout = findViewById(R.id.name_text_input_layout)
        nameTextInputEditText = findViewById(R.id.name_edit_text)
        passwordTextInputLayout = findViewById(R.id.pw_text_input_layout)
        passordTextInputEditText = findViewById(R.id.pw_edit_text)
        registerBtn = findViewById(R.id.register_btn)
        backBtn = findViewById(R.id.back_btn)
        progressBar = findViewById(R.id.register_progress_bar)
    }

    override fun showProgress() {
        progressBar.post {
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        progressBar.post {
            progressBar.visibility = View.GONE
        }
    }

    override fun setErrorRepeatedId() {
        accTextInputLayout.post {
            accTextInputLayout.error = resources.getString(R.string.repeated_username_error)
        }
    }

    override fun setErrorEmptyId() {
        accTextInputLayout.post {
            accTextInputLayout.error = resources.getString(R.string.blank_username_error)
        }
    }

    override fun setErrorEmptyName() {
        nameTextInputLayout.post {
            nameTextInputLayout.error = resources.getString(R.string.blank_name_error)
        }
    }

    override fun setErrorEmptyPassword() {
        passwordTextInputLayout.post {
            passwordTextInputLayout.error = resources.getString(R.string.blank_password_error)
        }
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_btn -> { finish() } //需再改

            R.id.register_btn -> {
                accTextInputLayout.isErrorEnabled = false
                nameTextInputLayout.isErrorEnabled = false
                passwordTextInputLayout.isErrorEnabled = false

                registerPresenter = RegisterPresenterImpl(this)
                registerPresenter.validateRegister(accTextInputEditText.text.toString().trim()
                    , nameTextInputEditText.text.toString().trim()
                    , passordTextInputEditText.text.toString().trim())
            }
        }
    }


}