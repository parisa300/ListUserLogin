package com.base.sampleapp_part.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.base.sampleapp_part.databinding.FragmentLoginBinding
import com.base.sampleapp_part.ui.viewmodel.LoginViewModel
import com.base.sampleapp_part.utils.AuthListener
import com.base.sampleapp_part.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), AuthListener {
    private var binding: FragmentLoginBinding by autoCleared()
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel.authListener = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onStarted()
    {

        binding.progressbar.visibility = View.VISIBLE

        context?.myToast("Login Started")

    }

    override fun onSuccess(loginResponseFromUserRepository: LiveData<String>)
    {

        loginResponseFromUserRepository.observe(this, Observer {
            binding.progressbar.visibility = View.GONE
            context?.myToast(it)

        }
        )
    }

    override fun onFailure(message: String)
    {
        binding.progressbar.visibility = View.GONE
       context?.myToast(message)

    }

    // functions to show show
    fun Context.myToast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}