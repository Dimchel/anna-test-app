package com.dimchel.annatest

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

object TestUtils {

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

    fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T
}