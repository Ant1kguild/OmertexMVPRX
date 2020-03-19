package com.omertex.test.app.di


import com.omertex.mvprx.data.network.interceptors.NetworkCheckInterceptor
import com.omertex.test.app.data.datasource.api.PlaceHolderFakeApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class InjectPlaceholder(
    private val loggingInterceptor: HttpLoggingInterceptor,
    private val networkCheckInterceptor: NetworkCheckInterceptor
) {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }


    private fun provideClient(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(networkCheckInterceptor)
        .build()

    private fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    fun providePlaceHolderApi(): PlaceHolderFakeApi =
        provideRetrofitInstance().create(PlaceHolderFakeApi::class.java)

}