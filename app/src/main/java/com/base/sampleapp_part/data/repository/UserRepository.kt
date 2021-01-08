package com.base.sampleapp_part.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.base.sampleapp_part.data.remote.ListService

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class UserRepository  @Inject constructor (private val listService: ListService){

    fun userLogin(email: String, password: String) : LiveData<String> {
        val loginResponse = MutableLiveData<String>()

        listService. userLogin(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                loginResponse.value = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                if (response.isSuccessful) {
                    loginResponse.value = response.body()?.string()
                } else {
                    loginResponse.value = response.errorBody()?.string()

                }
            }
        })

        return loginResponse
    }
}