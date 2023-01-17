package com.example.receiptpocket.login.Presenters


import com.example.receiptpocket.login.Views.LoginView
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test



class LoginPresenterImplTest {

    @MockK
    lateinit var loginView: LoginView
    lateinit var loginPresenter: LoginPresenterImpl

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        loginPresenter = LoginPresenterImpl(loginView)
    }

    @Test
    fun valiadateLoginWithEmptyUsernamePassword(){
        every {
            loginView.setErrorEmptyUserName()
        } just Runs

        every {
            loginView.setErrorEmptyPassword()
        } just Runs

        every {
            loginView.showProgress()
        } just Runs

        every {
            loginView.hideProgress()
        } just Runs


        loginPresenter.validateLogin("", "")


        verify {
            loginView.setErrorEmptyUserName()
            loginView.setErrorEmptyPassword()
        }
    }


    @Test
    fun validateLoginWithErrorUsername(){
        every {
            loginView.setErrorUserName()
        } just Runs

        every {
            loginView.showProgress()
        } just Runs

        every {
            loginView.hideProgress()
        } just Runs


        loginPresenter.validateLogin("iv", "123")

        verify {
            loginView.setErrorUserName()
        }

    }


}