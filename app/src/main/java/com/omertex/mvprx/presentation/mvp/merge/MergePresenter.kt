package com.omertex.mvprx.presentation.mvp.merge

import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.domain.GetMergeListUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit


@InjectViewState
class MergePresenter : MvpPresenter<MergeView>() , KoinComponent {

    private val getMergeListUseCase: GetMergeListUseCase by inject()
    private val schedulersProvider: SchedulersProvider by inject()

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()

    }

    private fun loadData() {
        viewState.showLoadingProgress()

        compositeDisposable.add(
            getMergeListUseCase
                .execute()
                .observeOn(schedulersProvider.ui())
                .subscribe(
                    this::onDataLoad,
                    this::onDataLoadError
                )
        )
    }

    private fun onDataLoad(data: List<MergeModel>) {
        viewState.setDataToRecycleView(data)
        viewState.hideLoadingProgress()
        viewState.showRecycleView(true)
    }


    private fun onDataLoadError(throwable: Throwable) {
        viewState.hideLoadingProgress()
        viewState.showError()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun setClick(clickMergeItem: Observable<MergeModel>) {
        compositeDisposable.add(
            clickMergeItem
                .throttleWithTimeout(300, TimeUnit.MILLISECONDS)
                .subscribe { viewState.openDetailsScreen(it) })
    }

}