package com.example.it_medicusassesmentproject.newTest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RerofitHelper2 {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://note3.itmedicus.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginApi = retrofit.create(LoginAPI::class.java)

}