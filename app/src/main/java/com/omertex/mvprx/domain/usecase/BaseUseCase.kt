package com.omertex.test.app.domain

import io.reactivex.rxjava3.core.Single


interface BaseUseCase<T> {
    fun execute(): Single<T>
}
