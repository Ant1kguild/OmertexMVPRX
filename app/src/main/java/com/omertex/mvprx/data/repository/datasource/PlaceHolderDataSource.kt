package com.omertex.test.app.data.datasource


import com.omertex.test.app.data.datasource.api.PlaceHolderApi
import com.omertex.test.app.data.model.placeholder.User
import io.reactivex.rxjava3.core.Single

class PlaceHolderDataSource(private val api: PlaceHolderApi) {
    fun users(): Single<List<User>> = api.getUserList()
}