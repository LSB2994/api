package com.example.remoteservisce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.remoteservisce.Api.AddUserScreen
import com.example.remoteservisce.Api.ApiScreen
import com.example.remoteservisce.Api.ApiViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val apiViewModel: ApiViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            UserScreen(userVm = viewModel)
            ApiScreen(userVm = apiViewModel)
            LaunchedEffect(Unit) {
                viewModel.getPosts()
            }
        }
    }
}

@Composable
fun UserScreen(
    userVm: MainViewModel
) {
    userVm.getPosts()
    LazyColumn(
        modifier = Modifier.padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(userVm.posts.value.size) {
            item ->
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                ){
                    Text(text = "Id : ${userVm.posts.value[item].id.toString()}" ?: " ")
                    Text(text = "Name : ${userVm.posts.value[item].name.toString()}" ?: " ")
                    Text(text = "Email : ${userVm.posts.value[item].email.toString()}" ?: " ")
                    Image(
                        painter = rememberAsyncImagePainter(userVm.posts.value[item].avatar),
                        contentDescription = null)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UserScreen(userVm = MainViewModel())
}