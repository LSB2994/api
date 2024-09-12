package com.example.remoteservisce

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api
    val posts: MutableState<List<UserResponse>> = mutableStateOf(emptyList())

    fun getPosts() {
        viewModelScope.launch {
            try {
                val response = apiService.getUser()
                if (response.isNotEmpty()) {
                    posts.value = response
                    Log.d(">>", "getPosts: ${response[0].name}")
                }
            } catch (e: Exception) {
                // Handle errors here
                Log.e(">>", "Error fetching posts: ${e.message}")
            }
        }
    }
}