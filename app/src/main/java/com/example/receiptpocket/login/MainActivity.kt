package com.example.receiptpocket.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import com.example.receiptpocket.R
import com.example.receiptpocket.login.Presenters.LoginPresenter
import com.example.receiptpocket.login.Presenters.LoginPresenterImpl
import com.example.receiptpocket.login.Views.LoginView
import com.example.receiptpocket.pocket.PocketActivity
import com.example.receiptpocket.prefs
import com.example.receiptpocket.register.RegisterActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), LoginView, View.OnClickListener {
    private lateinit var accTextInputLayout: TextInputLayout
    private lateinit var accEditText: TextInputEditText
    private lateinit var pwTextInputLayout: TextInputLayout
    private lateinit var pwEditText: TextInputEditText
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var loginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLoginOrNot()

        bindingViews()

        registerBtn.setOnClickListener(this)
        loginBtn.setOnClickListener(this)

    }

    private fun bindingViews(){
        accTextInputLayout = findViewById(R.id.acc_text_input_layout)
        accEditText = findViewById(R.id.acc_edit_text)
        pwTextInputLayout = findViewById(R.id.pw_text_input_layout)
        pwEditText = findViewById(R.id.pw_edit_text)
        registerBtn = findViewById(R.id.register_btn)
        loginBtn = findViewById(R.id.login_btn)
        progressBar = findViewById(R.id.login_progress_bar)
    }

    private fun checkLoginOrNot(){
        if(!prefs.userNamePrefs.equals("")){
            startActivity(Intent(this, PocketActivity::class.java))
        }
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

    override fun setErrorUserName() {
        accTextInputLayout.post {
            accTextInputLayout.error = resources.getString(R.string.username_error)
        }
    }

    override fun setErrorPassword() {
        pwTextInputLayout.post {
            pwTextInputLayout.error = resources.getString(R.string.password_error)
        }
    }

    override fun setErrorEmptyUserName() {
        accTextInputLayout.post {
            accTextInputLayout.error = resources.getString(R.string.blank_username_error)
        }
    }

    override fun setErrorEmptyPassword() {
        pwTextInputLayout.post {
            pwTextInputLayout.error = resources.getString(R.string.blank_password_error)
        }
    }

    override fun navigateToPocket() {
        startActivity(Intent(this, PocketActivity::class.java))
    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login_btn -> {
                // 每次按登入鍵都把error關掉
                accTextInputLayout.error = ""
                pwTextInputLayout.error = ""

                loginPresenter = LoginPresenterImpl(this)
                loginPresenter.validateLogin(accEditText.text.toString().trim()
                    , pwEditText.text.toString().trim())
            }

            R.id.register_btn -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }


}