package com.omertex.mvprx.data.network.interceptors

import com.omertex.mvprx.data.network.NetworkChecker
import com.omertex.mvprx.domain.global.exception.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkCheckInterceptor(private val networkChecker: NetworkChecker) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!networkChecker.isConnected()) {
            throw NoNetworkException("No network connection")
        }
        return try {
            chain.proceed(requestBuilder.build())
        } catch (e: SocketTimeoutException) {
            throw NoNetworkException()
        } catch (e: UnknownHostException) {
            throw NoNetworkException()
        }
    }
}