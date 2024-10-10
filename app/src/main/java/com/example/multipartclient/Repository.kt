package com.example.multipartclient

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class Repository {

    suspend fun uploadImage(file: File): Boolean {
        return try {
            ApiService.instance.uploadImage(
                MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    file.asRequestBody()
                )
            ).code() == 200
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e2: HttpException) {
            e2.printStackTrace()
            false
        }
    }

    suspend fun uploadImage2(message: String, file: File): String? {
        return try {
            ApiService.instance.uploadImage2(
                MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("message", message)
                    .addFormDataPart("image", file.name, file.asRequestBody())
                    .build()
            ).body()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e2: HttpException) {
            e2.printStackTrace()
            null
        }
    }

    suspend fun uploadImage3(message: String, file: File): String? {
        return try {
            ApiService.instance.uploadImage3(
                message,
                MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())
            ).body()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e2: HttpException) {
            e2.printStackTrace()
            null
        }
    }

    suspend fun uploadImage4(message: String, file: File): String? {
        return try {
            ApiService.instance.uploadImage4(
                RequestBody.create("text/plain".toMediaTypeOrNull(), message),
                MultipartBody.Part.createFormData("image", file.name, file.asRequestBody())
            ).body()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e2: HttpException) {
            e2.printStackTrace()
            null
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