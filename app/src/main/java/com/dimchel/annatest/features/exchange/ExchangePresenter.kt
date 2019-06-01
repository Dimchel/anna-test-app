package com.dimchel.annatest.features.exchange

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.dimchel.annatest.data.repositories.RatesRepository
import com.dimchel.annatest.di.AppScope
import javax.inject.Inject

@AppScope
class ExchangePresenter @Inject constructor(
    private val ratesRepository: RatesRepository
) : MvpPresenter<ExchangeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        ratesRepository.getEuroRates().subscribe {
            it.cube.cubes.forEach {
                Log.v("123123", "1: ${it.currency}")
            }
        }
    }
}