package com.dicoding.jlegends.retrofit

import com.dicoding.jlegends.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance: Api = retrofit.create(Api::class.java)
}