package com.example.it_medicusassesmentproject.testingpurpose

import com.example.it_medicusassesmentproject.model.Formdata
import com.example.it_medicusassesmentproject.secTest.DataClassModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("P-Auth-Token: P4t13nt4idu53r")
    @POST("api/register")
    fun getRegisterUser(
        @Body formdata: List<Formdata>
    ): Call<List<Formdata>>

}