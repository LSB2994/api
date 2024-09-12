package com.example.remoteservisce.Api

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.remoteservisce.MainViewModel
import java.time.LocalDateTime

@Composable
fun AddUserDialog(
    userVm: ApiViewModel,
    onDismissRequest: () -> Unit,
    onAddUser: (Data) -> Unit
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
                    val newUser = Data(title = title, content =  content, author = author, publishedDate =  publishedDate, views =  views.toLong()) // Create User object
                    onAddUser(newUser)
                    Log.d(">>", "AddUserDialog: $newUser")
                    userVm.addUser(newUser)
                    onDismissRequest() // Close dialog
                }) {
                    Text("Add User")
                }
            }
        }
    }
}

@Composable
fun AddUserScreen() {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AddUserDialog(
            userVm = ApiViewModel(),
            onDismissRequest = { showDialog = false },
            onAddUser = {
                showDialog = true
                // Handle addingthe user (e.g., to a list or database)
            }
        )
    }
    Button(onClick = { showDialog = true }) {
        Text("Add User")
    }
}