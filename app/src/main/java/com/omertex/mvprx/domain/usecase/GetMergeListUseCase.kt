package com.omertex.test.app.domain

import com.omertex.mvprx.domain.repositories.OxTestRepository
import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.data.model.MergeModelMapper
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

class GetMergeListUseCase(
    private val oxTestRepository: OxTestRepository,
    private val mergeModelMapper: MergeModelMapper,
    private val schedulersProvider: SchedulersProvider
) :
    BaseUseCase<List<MergeModel>> {

    override fun execute(): Single<List<MergeModel>> {
        return oxTestRepository.getUsers()
            .flatMap { users ->
                oxTestRepository.getPhoto(users.size)
                    .map { photos ->
                        Pair(first = users, second = photos)
                    }
            }
            .flatMap {
                Observable.zip(
                        Observable.fromIterable(it.first),
                        Observable.fromIterable(it.second),
                        BiFunction<User,Photo, MergeModel> { t1, t2 ->
                            mergeModelMapper.map(t1, t2)
                        }
                    )
                    .subscribeOn(schedulersProvider.computation())
                    .toList()
            }
    }
}


