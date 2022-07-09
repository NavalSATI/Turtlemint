package com.example.issue.network

import retrofit2.http.GET
import com.example.issue.model.Issues
import retrofit2.http.Url
import com.example.issue.model.Comments
import com.example.issue.network.API
import com.example.issue.network.RetrofitClient
import retrofit2.Call
import kotlin.jvm.Synchronized
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface API {
    @get:GET("issues")
    val issueDetails: Call<List<Issues?>?>?

    @GET
    fun getAllComments(@Url completeUrl: String?): Call<List<Comments?>?>?

    companion object {
        const val BASE_URL = "https://api.github.com/repos/square/okhttp/"
    }
}