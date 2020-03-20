package com.omertex.test.app.data.datasource.api


import com.omertex.test.app.data.model.placeholder.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface PlaceHolderApi {
    @Headers("Content-Type: application/json")
    @GET("users")
    fun getUserList() : Single<List<User>>
}