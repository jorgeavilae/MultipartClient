package com.example.multipartclient

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("/file")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<String>

    @POST("/file")
    suspend fun uploadImage2(
        @Body body: RequestBody
    ): Response<String>

    @Multipart
    @POST("/file")
    suspend fun uploadImage3(
        @Part("message") message: String,
        @Part image: MultipartBody.Part
    ): Response<String>

    @Multipart
    @POST("/file")
    suspend fun uploadImage4(
        @Part("message") message: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<String>

    @GET("/")
    suspend fun helloWorld(): Response<String>

    companion object {
        val instance by lazy {
            getRetrofit().create(ApiService::class.java)
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl()
                //.baseUrl("http://0.0.0.0") TODO use your own IP address
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
        }

        private fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }
    }
}