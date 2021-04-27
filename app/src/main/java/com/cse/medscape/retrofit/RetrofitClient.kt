package com.cse.medscape.retrofit

import com.cse.medscape.helper.AuthorizationHelper
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private lateinit var retrofitClient: Retrofit

        private val BASE_URL = "https://api.infermedica.com/v2/"

        fun getRetrofitClient(): Retrofit {

            if (!Companion::retrofitClient.isInitialized) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = (HttpLoggingInterceptor.Level.BODY)

                val client = OkHttpClient.Builder()
                    .addInterceptor(AuthorizationHelper()).addInterceptor(interceptor).build()

                val gson = GsonBuilder().setLenient()
                    .create()

                retrofitClient = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }
            return retrofitClient
        }

        fun getApiService(): InfermedicaApiService {
            return getRetrofitClient().create(InfermedicaApiService::class.java)
        }
    }

}