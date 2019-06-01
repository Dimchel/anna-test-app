package com.dimchel.annatest.common

import com.dimchel.annatest.di.AppScope
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AppScope
class AppSchedulersImpl @Inject constructor() : AppSchedulers {

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
    override fun computation(): Scheduler = Schedulers.computation()

}