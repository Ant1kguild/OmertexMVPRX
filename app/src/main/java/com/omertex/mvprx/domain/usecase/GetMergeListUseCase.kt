package com.omertex.test.app.domain

import com.omertex.mvprx.domain.repositories.OxTestRepository
import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import io.reactivex.rxjava3.core.Single

class GetMergeListUseCase(
    private val oxTestRepository: OxTestRepository,
    private val schedulersProvider: SchedulersProvider
) :
    BaseUseCase<Pair<List<User>, List<Photo>>> {


    override fun execute(): Single<Pair<List<User>, List<Photo>>> {
        return oxTestRepository.getUsers()
            .concatMap { users ->
                oxTestRepository.getPhoto(users.size)
                    .map { photos ->
                        Pair(first = users, second = photos)
                    }
            }.subscribeOn(schedulersProvider.io())
    }
}