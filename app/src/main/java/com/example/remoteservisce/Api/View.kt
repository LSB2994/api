package com.example.remoteservisce.Api

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.remoteservisce.MainViewModel
import java.time.LocalDateTime

@Composable
fun ApiScreen(
    userVm: ApiViewModel
) {
    userVm.getPosts()
    val users = userVm.posts.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.padding(bottom =10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(users.value) { item ->
            var expanded by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    item.imageUrl?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Text(
                        text = item.title ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "By ${item.author ?: ""}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = item.content ?: "", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.publishedDate ?: "",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Views: ${item.views ?: ""}",
                                style = MaterialTheme.typography.titleSmall)
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "More")
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Update") },
                                    onClick = {

                                        // Handle update action
                                        showDialog = true
                                    }
                                )

                                if (showDialog) {
                                    UpdateDialog(
                                        updateId = item.id?:0,
                                        userVm = userVm,
                                        onDismissRequest = { showDialog = false },
                                        onAddUser = {
                                            showDialog = true
                                        }
                                    )
                                }
                                DropdownMenuItem(
                                    text = { Text("Delete") },
                                    onClick = {
                                        // Handle delete action
                                        item.id?.let {
                                            userVm.deleteUser(it)
                                        }
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        AddUserScreen()
    }

}
@Composable
fun UpdateDialog(
    updateId:Long,
    userVm: ApiViewModel,
    onDismissRequest: () -> Unit,
    onAddUser: (ArticleRequest) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publishedDate by remember { mutableStateOf(LocalDateTime.now().toString()) }
    var views by remember { mutableStateOf("") }
    var isPublished by remember { mutableStateOf("") }
    // Add more fields as needed

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false // For full screen
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(0.dp) // Remove rounded corners
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment= Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("content") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = author,
                    onValueChange = { author = it },
                    label = { Text("author") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = publishedDate,
                    onValueChange = { publishedDate = it },
                    label = { Text("publishedDate") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = views,
                    onValueChange = { views = it },
                    label = { Text("views") }
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    val newUser = ArticleRequest(title = title, content =  content, author = author, publishedDate =  publishedDate, views =  views.toLong()) // Create User object
                    onAddUser(newUser)
                    Log.d(">>", "AddUserDialog: $newUser")
                    userVm.updateArticle(id = updateId, article = newUser)
                    onDismissRequest() // Close dialog
                }) {
                    Text("Add User")
                }
            }
        }
    }
}

@Composable
fun UpdateScreen(id:Long) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        UpdateDialog(
            updateId = id,
            userVm = ApiViewModel(),
            onDismissRequest = { showDialog = false },
            onAddUser = {
                showDialog = true
            }
        )
    }
    Button(onClick = { showDialog = true }) {
        Text("Ok")
    }
}