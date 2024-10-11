package com.example.multipartclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(
    private val repository: Repository = Repository()
): ViewModel() {

    private val _text = MutableLiveData<String?>(null)
    val text: LiveData<String?> get() = _text

    fun helloWorld() {
        viewModelScope.launch(Dispatchers.IO) {
            _text.postValue(repository.helloWorld())
        }
    }

    fun uploadImage(file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            _text.postValue(repository.uploadImage(file).toString())
        }
    }

    fun uploadImage2(file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            _text.postValue(repository.uploadImage2(System.currentTimeMillis().toString(), file))
        }
    }

    fun uploadImage3(file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            _text.postValue(repository.uploadImage3(System.currentTimeMillis().toString(), file))
        }
    }

    fun uploadImage4(file: File) {
        viewModelScope.launch(Dispatchers.IO) {
            _text.postValue(repository.uploadImage4(System.currentTimeMillis().toString(), file))
        }
    }

    fun uploadTwoImages(file: File, file2: File) {
        viewModelScope.launch(Dispatchers.IO) {
            _text.postValue(repository.uploadTwoImages(
                System.currentTimeMillis().toString(),
                file,
                file2
            ))
        }
    }
}