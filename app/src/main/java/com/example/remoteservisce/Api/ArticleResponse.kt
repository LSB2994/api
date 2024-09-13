package com.example.remoteservisce.Api

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("message")
    val message:String,
    @SerializedName("payload")
    val payload:List<Article>,
    @SerializedName("status")
    val status:String,
    @SerializedName("timestamp")
    val timestamp:String
)
