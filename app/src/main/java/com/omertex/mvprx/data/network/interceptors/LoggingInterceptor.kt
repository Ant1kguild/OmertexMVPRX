package com.omertex.mvprx.data.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {
    fun loggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }
}