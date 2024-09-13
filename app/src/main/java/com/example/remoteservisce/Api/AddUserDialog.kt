package com.example.remoteservisce.Api

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.time.LocalDateTime

@Composable
fun AddUserDialog(
    userVm: ApiViewModel,
    onDismissRequest: () -> Unit,
    onAddUser: (ArticleRequest) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf(mutableStateOf<Uri?>(null)) }
    var author by remember { mutableStateOf("") }
    var publishedDate by remember { mutableStateOf(LocalDateTime.now().toString()) }
    var views by remember { mutableStateOf("") }
    var isPublished by remember { mutableStateOf("") }
    var showImagePicker by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Button(onClick = { showImagePicker = true }) {
//                    Text("Select Image")
//
//                    imageUrl?.let {
//                        AsyncImage(
//                            model = it,
//                            contentDescription = null,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(200.dp)
//                        )
//                    }
//
//                }
//                val launcher =
//                rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
//                    imageUrl = uri.let { mutableStateOf(it) }
//                }

//                if (showImagePicker) {
//                    launcher.launch("image/*")
//                }

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
                        val newUser = ArticleRequest(
                            title = title,
                            content = content,
                            author = author,
                            publishedDate = publishedDate,
                            views = views.toLong()
                        ) // Create User object
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
//        Spacer(modifier = Modifier.height(16.dp))

//        if (isLoading) {
//            CircularProgressIndicator()
//        } else {
//            Button(onClick = {
//                val newUser = ArticleRequest(
//                    title = title,
//                    content = content,
//                    author = author,
//                    publishedDate = publishedDate,
//                    views = views.toLongOrNull() ?: 0
//                )
//
//                isLoading = true
//
//                CoroutineScope(Dispatchers.IO).launch {
//                    try {
//                        val imageFile = imageUrl?.let { uri ->
//                            context.contentResolver.openInputStream(uri)?.use { inputStream ->
//                                File.createTempFile(
//                                    "image",
//                                    ".jpg",
//                                    context.cacheDir
//                                ).apply {
//                                    inputStream.copyTo(outputStream())
//                                }
//                            }
//                        }
//
//                        val requestBody = imageFile?.asRequestBody("image/*".toMediaTypeOrNull())
//                        val imagePart = requestBody?.let {
//                            MultipartBody.Part.createFormData(
//                                "image",
//                                imageFile.name,
//                                it
//                            )
//                        }
//
//                        val response = apiService.uploadImage(imagePart)
//                        if (response.isSuccessful) {
//                            val imageUrl = response.body() ?: ""
//                            onAddUser(newUser.copy(imageUrl = imageUrl))
//                            userVm.addUser(newUser.copy(imageUrl = imageUrl))
//                        } else {
//                            // Handle error
//                            Log.e("UploadError", "Error uploading image: ${response.message()}")
//                        }
//                    } catch (e: Exception) {// Handle error
//                        Log.e("UploadError", "Error uploading image: ${e.message}")
//                    } finally {
//                        isLoading = false
//                        onDismissRequest()
//                    }
//                }
//            }) {
//                Text("Add User")
//            }
//        }

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

