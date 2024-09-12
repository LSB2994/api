package com.example.remoteservisce.Api

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticlesResponse(
    @SerializedName("id")
    var id:Long?=null,
    @SerializedName("title")
    var title:String?=null,
    @SerializedName("content")
    var content:String?=null,
    @SerializedName("imageUrl")
    var imageUrl:String?=null,
    @SerializedName("author")
    var author:String?=null,
    @SerializedName("publishedDate")
    var publishedDate:String?=null,
    @SerializedName("views")
    var views:Long?=null,
    @SerializedName("isPublished")
    var isPublished:Boolean?=null
)