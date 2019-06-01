package com.dimchel.annatest.common

import io.reactivex.Scheduler

interface AppSchedulers {

    fun main(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler

}