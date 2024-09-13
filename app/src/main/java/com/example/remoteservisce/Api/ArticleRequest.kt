package com.example.remoteservisce.Api

import androidx.room.PrimaryKey

data class ArticleRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0 ,
    val title: String,
    val content: String,
    val imageUrl: String?=null,
    val author: String,
    val publishedDate: String,
    val views: Long,
    val isPublished: Boolean=true
)