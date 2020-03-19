package com.omertex.mvprx.domain.global.exception

class NoNetworkException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}