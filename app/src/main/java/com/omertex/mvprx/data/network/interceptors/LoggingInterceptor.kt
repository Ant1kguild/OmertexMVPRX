package com.omertex.mvprx.data.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {
    private fun loggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }
}