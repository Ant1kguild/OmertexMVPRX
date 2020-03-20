package com.omertex.mvprx.presentation.mvp.global

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulersProvider {

    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun newThread(): Scheduler {
        return Schedulers.newThread()
    }

    fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }
}