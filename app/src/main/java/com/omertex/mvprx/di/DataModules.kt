package com.omertex.mvprx.di

import com.omertex.mvprx.data.network.NetworkChecker
import com.omertex.mvprx.data.network.interceptors.LoggingInterceptor
import com.omertex.mvprx.data.network.interceptors.NetworkCheckInterceptor
import com.omertex.mvprx.data.repository.OxTestRepositoryImpl
import com.omertex.mvprx.domain.repositories.OxTestRepository
import com.omertex.mvprx.presentation.mvp.global.SchedulersProvider
import com.omertex.mvprx.presentation.mvp.merge.MergePresenter
import com.omertex.test.app.data.datasource.PlaceHolderDataSource
import com.omertex.test.app.data.datasource.UnsplashDataSource
import com.omertex.test.app.data.model.MergeModelMapper
import com.omertex.test.app.di.InjectPlaceholder
import com.omertex.test.app.di.InjectUnsplash
import com.omertex.test.app.domain.GetMergeListUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val utilsModule = module {
    factory { MergeModelMapper() }
    factory { SchedulersProvider() }
}


val interceptorModule = module {
    factory { NetworkChecker(context = androidContext()) }
    factory { LoggingInterceptor.loggerInterceptor() }
    factory { NetworkCheckInterceptor(networkChecker = get()) }
}


val apiModule = module {
    single {
        InjectPlaceholder(
            loggingInterceptor = get(),
            networkCheckInterceptor = get()
        )
            .providePlaceHolderApi()
    }
    single {
        InjectUnsplash(
            loggingInterceptor = get(),
            networkCheckInterceptor = get()
        )
            .provideUnsplashApi()
    }

}

val dataSourceModule = module {
    single {
        PlaceHolderDataSource(
            api = get(),
            schedulersProvider = get()
        )
    }
    single {
        UnsplashDataSource(
            api = get(),
            schedulersProvider = get()
        )
    }
}

val repositoryModule = module {
    single<OxTestRepository> {
        OxTestRepositoryImpl(
            placeHolderDataSource = get(),
            unsplashDataSource = get()
        )
    }
}


val useCaseModule = module {
    factory {
        GetMergeListUseCase(
            oxTestRepository = get(),
            mergeModelMapper = get(),
            schedulersProvider = get()
        )
    }
}

val presentersModule = module {
    factory { MergePresenter() }
}

val listModules = listOf(
    utilsModule,
    interceptorModule,
    apiModule,
    dataSourceModule,
    repositoryModule,
    useCaseModule,
    presentersModule
)