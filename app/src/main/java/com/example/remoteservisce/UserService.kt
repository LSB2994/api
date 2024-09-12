package com.example.remoteservisce

import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getUser():List<UserResponse>
}