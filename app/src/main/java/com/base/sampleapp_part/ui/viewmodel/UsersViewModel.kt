package com.base.sampleapp_part.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.base.sampleapp_part.data.repository.ListRepository

class UsersViewModel @ViewModelInject constructor (

    private val repository: ListRepository
) : ViewModel() {

    val users = repository.getUsers()
}
