package com.example.remoteservisce.Api

import androidx.room.PrimaryKey

data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0 ,
    val title: String,
    val content: String,
    val imageUrl: String?=null,
    val author: String,
    val publishedDate: String,
    val views: Long,
    val isPublished: Boolean=true
)