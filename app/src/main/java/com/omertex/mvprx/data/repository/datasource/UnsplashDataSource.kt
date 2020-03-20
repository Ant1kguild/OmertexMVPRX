package com.omertex.test.app.data.datasource


import com.omertex.test.app.data.datasource.api.UnsplashApi

class UnsplashDataSource(private val api: UnsplashApi) {
    fun photos(numberOfPhotos: Int) = api.getListPhotos(pageSize = numberOfPhotos)
}