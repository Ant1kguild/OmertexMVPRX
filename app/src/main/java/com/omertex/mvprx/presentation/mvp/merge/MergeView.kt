package com.omertex.mvprx.presentation.mvp.merge

import com.omertex.test.app.data.model.MergeModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MergeView : MvpView {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showRecycleView(enable: Boolean)
    fun setDataToRecycleView(data: List<MergeModel>)
    fun openDetailsScreen(data: MergeModel)
}