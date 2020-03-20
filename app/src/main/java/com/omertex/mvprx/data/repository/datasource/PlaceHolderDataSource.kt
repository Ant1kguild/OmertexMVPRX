package com.omertex.test.app.data.datasource


import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.test.app.data.datasource.api.PlaceHolderApi
import com.omertex.test.app.data.model.placeholder.User
import io.reactivex.rxjava3.core.Single

class PlaceHolderDataSource(
    private val api: PlaceHolderApi,
    private val schedulersProvider: SchedulersProvider
) {
    fun users(): Single<List<User>> = api.getUserList()
        .subscribeOn(schedulersProvider.io())
}