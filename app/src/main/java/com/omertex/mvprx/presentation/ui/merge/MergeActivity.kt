package com.omertex.mvprx.presentation.ui.merge

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.omertex.mvprx.R
import com.omertex.mvprx.databinding.ActivityMergeBinding
import com.omertex.mvprx.presentation.mvp.merge.MergePresenter
import com.omertex.mvprx.presentation.mvp.merge.MergeView
import com.omertex.mvprx.presentation.ui.BaseMvpActivity
import com.omertex.mvprx.presentation.ui.details.DetailsActivity
import com.omertex.mvprx.presentation.ui.global.adapter.MergeAdapter
import com.omertex.test.app.data.model.MergeModel
import com.roger.catloadinglibrary.CatLoadingView
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject

class MergeActivity : BaseMvpActivity(), MergeView {

    private lateinit var binding: ActivityMergeBinding
    private var loadingView: CatLoadingView? = null

    private val clickMergeItem = PublishSubject.create<MergeModel>()

    private val mergeAdapter: MergeAdapter by lazy {
        MergeAdapter { data ->
            clickMergeItem.onNext(data)
        }
    }

    private val presenterProvider: MergePresenter by inject()

    private val presenter by moxyPresenter { presenterProvider }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setClick(clickMergeItem)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_merge)

        binding.rvMerge.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mergeAdapter

        }
    }

    override fun showLoadingProgress() {
        val target = this
        val progress = loadingView ?: CatLoadingView().apply {
            setCanceledOnTouchOutside(false)
            show(target.supportFragmentManager, "")
        }
        loadingView = progress
    }

    override fun hideLoadingProgress() {
        loadingView?.dismiss()
        loadingView = null
    }

    override fun showRecycleView(enable: Boolean) {
        if (enable) {
            binding.rvMerge.visibility = View.VISIBLE
        } else {
            binding.rvMerge.visibility = View.INVISIBLE
        }
    }

    override fun setDataToRecycleView(data: List<MergeModel>) {
        mergeAdapter.setData(data)
    }

    override fun openDetailsScreen(data: MergeModel) {
        val intent = DetailsActivity.getInstance(this@MergeActivity, data)
        startActivity(intent)
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }


}
