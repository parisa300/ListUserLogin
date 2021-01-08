package com.base.sampleapp_part.data.remote

import com.base.sampleapp_part.data.entities.dataList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ListService {
    @GET("users")
    suspend fun getAllLists() : Response<dataList>

    @FormUrlEncoded
    @POST("login")
   fun userLogin(

        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("register")
   fun userRegister(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<ResponseBody>
}