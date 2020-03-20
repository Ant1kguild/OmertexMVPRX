package com.omertex.test.app.data.datasource


import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.test.app.data.datasource.api.UnsplashApi

class UnsplashDataSource(
    private val api: UnsplashApi,
    private val schedulersProvider: SchedulersProvider
) {
    fun photos(numberOfPhotos: Int) = api.getListPhotos(pageSize = numberOfPhotos)
        .subscribeOn(schedulersProvider.io())
}