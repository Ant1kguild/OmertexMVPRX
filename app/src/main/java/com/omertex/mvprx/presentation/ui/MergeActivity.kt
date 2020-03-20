package com.omertex.mvprx.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omertex.mvprx.R
import androidx.databinding.DataBindingUtil
import com.omertex.mvprx.databinding.ActivityMergeBinding

class MergeActivity : BaseMvpActivity() {

    private lateinit var binding: ActivityMergeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_merge)
    }
}
