package com.omertex.test.app.di


import com.omertex.test.app.data.datasource.api.UnsplashApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object InjectUnsplash {
    private const val BASE_URL = "https://api.unsplash.com/"

    private fun loggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }

    private fun provideClient(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggerInterceptor())
        .build()

    private fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun provideUnsplashApi(): UnsplashApi =
        provideRetrofitInstance()
            .create(UnsplashApi::class.java)
}