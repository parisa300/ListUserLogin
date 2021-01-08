package com.base.sampleapp_part.ui.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.base.sampleapp_part.data.repository.UserRepository
import com.base.sampleapp_part.utils.AuthListener

class LoginViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) :ViewModel() {

    var email: String? = null
    var password: String? = null


    var authListener: AuthListener? = null


    fun onLoginButtonClick(view: View)
    {

        authListener?.onStarted()


        if (email.isNullOrEmpty() || password.isNullOrEmpty())
        {

            authListener?.onFailure("Invalid Username or Password")

            return
        }
        else
        {

            val loginResponseFromUserRepository = repository.userLogin(email!!, password!!)
            authListener?.onSuccess(loginResponseFromUserRepository)

        }

    }
}