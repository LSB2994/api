package com.example.remoteservisce.Api

import com.example.remoteservisce.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiArticle {
    private
    const val API_URL = "http://110.74.194.123:8080/api/v1/"
    val apiNew: UserServiceApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(UserServiceApi::class.java)
    }
}