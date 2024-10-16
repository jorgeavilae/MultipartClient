package com.example.multipartclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.multipartclient.ui.theme.MultipartClientTheme
import java.io.File

class MainActivity : ComponentActivity() {
    val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MultipartClientTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val text = viewModel.text.observeAsState().value
                    Content(
                        text = text,
                        modifier = Modifier.padding(innerPadding),
                        onTestConnection = {
                            viewModel.helloWorld()
                        },
                        onUploadImageMultipart = {
                            val file = File(cacheDir, "myImage.jpg")
                            file.createNewFile()
                            file.outputStream().use {
                                assets.open("image.jpg").copyTo(it)
                            }
                            viewModel.uploadImage(file)
                        },
                        onUploadImageBody = {
                            val file = File(cacheDir, "myImage.jpg")
                            file.createNewFile()
                            file.outputStream().use {
                                assets.open("image.jpg").copyTo(it)
                            }
                            viewModel.uploadImage2(file)
                        },
                        onUploadImageParamsPrimitivesMultipart = {
                            val file = File(cacheDir, "myImage.jpg")
                            file.createNewFile()
                            file.outputStream().use {
                                assets.open("image.jpg").copyTo(it)
                            }
                            viewModel.uploadImage3(file)
                        },
                        onUploadImageParamsRequestBodyMultipart = {
                            val file = File(cacheDir, "myImage.jpg")
                            file.createNewFile()
                            file.outputStream().use {
                                assets.open("image.jpg").copyTo(it)
                            }
                            viewModel.uploadImage4(file)
                        },
                        onUploadTwoImagesMultipart = {
                            val file = File(cacheDir, "myImage.jpg")
                            file.createNewFile()
                            file.outputStream().use {
                                assets.open("image.jpg").copyTo(it)
                            }
                            val file2 = File(cacheDir, "myImage2.png")
                            file2.createNewFile()
                            file2.outputStream().use {
                                assets.open("img.png").copyTo(it)
                            }
                            viewModel.uploadTwoImages(file, file2)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Content(
    text: String?,
    modifier: Modifier,
    onTestConnection: () -> Unit,
    onUploadImageMultipart: () -> Unit,
    onUploadImageBody: () -> Unit,
    onUploadImageParamsPrimitivesMultipart: () -> Unit,
    onUploadImageParamsRequestBodyMultipart: () -> Unit,
    onUploadTwoImagesMultipart: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text?:"",
            modifier = modifier
        )
        Button(onClick = { onTestConnection() }) {
            Text(text = "Test connection")
        }
        Button(onClick = { onUploadImageMultipart() }) {
            Text(text = "Upload image Multipart")
        }
        Button(onClick = { onUploadImageBody() }) {
            Text(text = "Upload image Body")
        }
        Button(onClick = { onUploadImageParamsPrimitivesMultipart() }) {
            Text(text = "Upload image Parameters as Primitives Multipart")
        }
        Button(onClick = { onUploadImageParamsRequestBodyMultipart() }) {
            Text(text = "Upload image Parameters as RequestBody Multipart")
        }
        Button(onClick = { onUploadTwoImagesMultipart() }) {
            Text(text = "Upload 2 images Multipart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultipartClientTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Content(
                text = "text",
                modifier = Modifier.padding(innerPadding),
                onTestConnection = {},
                onUploadImageMultipart = {},
                onUploadImageBody = {},
                onUploadImageParamsPrimitivesMultipart = {},
                onUploadImageParamsRequestBodyMultipart = {},
                onUploadTwoImagesMultipart = {},
            )
        }
    }
}