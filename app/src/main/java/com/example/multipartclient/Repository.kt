package com.example.multipartclient

import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import okhttp3.RequestBody.Companion.asRequestBody

class Repository {

    suspend fun uploadImage(file: File) {
        return try {
            ApiService.instance.uploadImage(
                MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    file.asRequestBody()
                )
            )

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e2: HttpException) {
            e2.printStackTrace()
        }
    }

    suspend fun helloWorld(): String? {
        return try {
            ApiService.instance.helloWorld().body()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e2: HttpException) {
            e2.printStackTrace()
            null
        }
    }
}