package com.omertex.mvprx.domain.repositories



import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import io.reactivex.rxjava3.core.Single

interface OxTestRepository {

    fun getUsers() : Single<List<User>>

    fun getPhoto(numberOfPhotos : Int) : Single<List<Photo>>
}