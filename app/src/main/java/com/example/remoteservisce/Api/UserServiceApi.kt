package com.example.remoteservisce.Api

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UserServiceApi {
    @GET("articles")
    suspend fun getArticles():ArticleResponse
    @POST("articles")
    suspend fun postUser(@Body user: ArticleRequest): ArticleResponse
    @PUT("articles/{id}")
    suspend fun updateArticle(@Path("id") id: Long, @Body article: ArticleRequest): ArticleResponse
    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id") id: Long): ArticleResponse
    @POST("images/upload")
    suspend fun getImageUrl(@Part image: MultipartBody.Part): ArticleResponse

}