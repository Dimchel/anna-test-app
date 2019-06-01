package com.dimchel.annatest

import android.app.Application
import com.dimchel.annatest.di.AppComponent
import com.dimchel.annatest.di.DaggerAppComponent

class AnnaApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDi()
    }

    private fun initDi() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}