package com.omertex.mvprx.data.repository

import com.omertex.mvprx.domain.repositories.OxTestRepository
import com.omertex.test.app.data.datasource.PlaceHolderDataSource
import com.omertex.test.app.data.datasource.UnsplashDataSource
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import io.reactivex.rxjava3.core.Single


class OxTestRepositoryImpl(
    private val placeHolderDataSource: PlaceHolderDataSource,
    private val unsplashDataSource: UnsplashDataSource
) : OxTestRepository {

    override fun getUsers(): Single<List<User>> {
        return placeHolderDataSource.users()
    }

    override fun getPhoto(numberOfPhotos: Int): Single<List<Photo>> {
        return unsplashDataSource.photos(numberOfPhotos)
    }
}