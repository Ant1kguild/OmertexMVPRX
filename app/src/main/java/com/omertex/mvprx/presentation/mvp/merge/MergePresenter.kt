package com.omertex.mvprx.presentation.mvp.merge

import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.data.model.MergeModelMapper
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import com.omertex.test.app.domain.GetMergeListUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MergePresenter(
    private val getMergeListUseCase: GetMergeListUseCase,
    private val schedulersProvider: SchedulersProvider,
    private val mergeModelMapper: MergeModelMapper
) :
    MvpPresenter<MergeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        viewState.showLoadingProgress()
        getMergeListUseCase.execute()
            .subscribe(this::onDataLoad, this::onDataLoadError)
    }

    private fun onDataLoad(data: Pair<List<User>, List<Photo>>) {
        Observable.zip(
                Observable.fromIterable(data.first),
                Observable.fromIterable(data.second),
                BiFunction { user, photo ->
                    return@BiFunction mergeModelMapper.map(user, photo)
                }
            )
            .subscribeOn(schedulersProvider.computation())
            .toList()
            .observeOn(schedulersProvider.ui())
            .subscribe({
                viewState.setDataToRecycleView(it)
                viewState.hideLoadingProgress()
                viewState.showRecycleView(true)
            }, {
                //TODO
            })

    }

    private fun onDataLoadError(throwable: Throwable) {

    }

}