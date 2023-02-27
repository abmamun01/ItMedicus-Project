package com.example.it_medicusassesmentproject.newTest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface LoginAPI {
    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse2


    @POST("api/register")
    fun registerUser(
        @Body user: UserModel
    ): Call<ResponseBody>
}
