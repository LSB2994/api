package com.example.remoteservisce.Api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApiViewModel: ViewModel() {
    private val apiService = ApiArticle.apiNew
    val posts: MutableState<List<ArticlesResponse>> = mutableStateOf(emptyList())

    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getArticles()
                if (response.payload.isNotEmpty()) {
                    posts.value = response.payload
                    Log.d(">>", "getPosts: ${response.payload[0].title}")
                }
            } catch (e: Exception) {
                // Handle errors here
                Log.e(">>", "Error fetching posts: ${e.message}")
            }
        }
    }
    fun addUser(data: Data) {
        viewModelScope.launch {
            try {
                val response = apiService.postUser(data)
                Log.d(">>", "addUser: $response")

            } catch (e: Exception) {
                // Handle errors here
                Log.e(">>", "Error fetching posts: ${e.message}")
            }
        }
    }
}