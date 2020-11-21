package com.example.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  Builds a retrofit [ApiService] instance
 */
object NetworkFactory {

    fun makeHttpLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.MINUTES)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService {
        val retrofit = Retrofit
                .Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(ApiService::class.java)

    }

    open fun provideRetrofitApiService(isDebug: Boolean): ApiService {
        val okHttpClient = provideOkHttpClient(makeHttpLoggingInterceptor(isDebug))
        return provideApiService(okHttpClient, Gson())
    }
}
