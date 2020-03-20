package com.omertex.mvprx.presentation.ui

import android.content.Context
import moxy.MvpAppCompatActivity

abstract class BaseMvpActivity : MvpAppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }
}