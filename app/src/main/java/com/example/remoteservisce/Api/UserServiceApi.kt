package com.example.remoteservisce.Api

import com.example.remoteservisce.UserResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserServiceApi {
    @GET("articles")
    suspend fun getArticles():Response
    @POST("articles")
    suspend fun postUser(@Body user: Data): Response
    @PUT("articles/{id}")
    suspend fun updateArticle(@Path("id") id: Int, @Body article: ArticlesResponse): ArticlesResponse
    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id") id: Int): ArticlesResponse
}