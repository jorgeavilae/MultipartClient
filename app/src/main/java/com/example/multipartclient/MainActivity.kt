package com.example.multipartclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                        onUploadImage = {
                            val file = File(cacheDir, "myImage.jpg")
                            file.createNewFile()
                            file.outputStream().use {
                                assets.open("image.jpg").copyTo(it)
                            }
                            viewModel.uploadImage(file)
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
    onUploadImage: () -> Unit
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
        Button(onClick = { onUploadImage() }) {
            Text(text = "Upload image")
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
                onUploadImage = {}
            )
        }
    }
}