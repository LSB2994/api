package com.example.remoteservisce.Api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiViewModel: ViewModel() {
    private val apiService = ApiArticle.apiNew

    private val _posts = MutableStateFlow<List<Article>>(emptyList())
    val posts: StateFlow<List<Article>> get() = _posts
//    val posts: LiveData<List<Article>> get() = _posts

    init {
        getPosts()
    }
    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getArticles()
                if (response.payload.isNotEmpty()) {
                    _posts.value = response.payload
                    Log.d(">>", "getPosts: ${response.payload[0].title}")
                }
            } catch (e: Exception) {
                // Handle errors here
                Log.e(">>", "Error fetching posts: ${e.message}")
            }
        }
    }
    fun addUser(articleRequest: ArticleRequest) {
        viewModelScope.launch {
            try {
                val response = apiService.postUser(articleRequest)
                Log.d(">>", "addUser: $response")
                getPosts()

            } catch (e: Exception) {
                // Handle errors here
                Log.e(">>", "Error fetching posts: ${e.message}")
            }
        }
    }

    fun deleteUser(id: Long){
        viewModelScope.launch {
            try {
                val response = apiService.deleteArticle(id)
                Log.d(">>", "deleteUser: $response")
                getPosts()
            }catch (e:Exception){
                Log.e(">>", "Error fetching posts")
            }
        }
    }

    fun updateArticle(id: Long, article: ArticleRequest){
        viewModelScope.launch {
            try {
                val response = apiService.updateArticle(id, article)
                Log.d(">>", "Update: $response")
                getPosts()
            }catch (e:Exception){
                Log.e(">>", "Error fetching posts")
            }
        }
    }
}