package com.example.remoteservisce.Api

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.remoteservisce.MainViewModel

@Composable
fun ApiScreen(
    userVm: ApiViewModel
) {
    userVm.getPosts()
    val user = userVm.posts.value
    LazyColumn(
        modifier = Modifier.padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(user){
            item ->
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ){
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                ){
                    item.imageUrl?.let {
                        Image(
                                painter = rememberAsyncImagePainter(it),
                        contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                        )
                    }
                    Text(text = "Title : ${item.title.toString()}" ?: " ")
                    Text(text = "Content : ${item.content.toString()}" ?: " ")
                }
            }
        }
    }
    AddUserScreen()
}