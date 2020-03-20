package com.omertex.test.app.data.datasource.api


import com.omertex.mvprx.BuildConfig
import com.omertex.test.app.data.model.unsplash.Photo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {
    @Headers("Content-Type: application/json")
    @GET("photos")
    fun getListPhotos(
        @Query("client_id") client_id : String = BuildConfig.KEY_API_ACCESES,
        @Query("per_page") pageSize: Int
    ): Single<List<Photo>>
}