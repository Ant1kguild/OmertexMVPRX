package com.omertex.mvprx.domain.global.mappers

interface BaseMapper<in A : Any, out B : Any> {
    fun map(from: A): B
}

interface BaseMapperPair<in A : Any, in B : Any, out C : Any> {
    fun map(fromFirst: A, fromSecond : B): C
}