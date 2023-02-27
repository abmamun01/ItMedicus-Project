package com.example.it_medicusassesmentproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// this will return retrofit object
object RetrofitHelper {

    val baseurl = "https://note3.itmedicus.org/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}