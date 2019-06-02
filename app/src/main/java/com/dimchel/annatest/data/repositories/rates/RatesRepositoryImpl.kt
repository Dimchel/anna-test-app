package com.dimchel.annatest.data.repositories.rates

import android.util.Log
import com.dimchel.annatest.data.api.AnnaApiProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    annaApiProvider: AnnaApiProvider
) : RatesRepository {

    companion object {
        private const val RATES_REQUEST_PERIOD_ON_SECONDS = 5L
    }

    private val cachedRates: Subject<List<RateModel>> = BehaviorSubject.create()

    override fun getEuroRates(): Observable<List<RateModel>> =
        if (!cachedRates.hasComplete()) {
            requestRatesObservable.doOnNext {
                cachedRates.onNext(it)

                val timerDisposable = Observable
                    .interval(0, RATES_REQUEST_PERIOD_ON_SECONDS, TimeUnit.SECONDS)
                    .subscribe {
                        requestRatesObservable.subscribe({
                            cachedRates.onNext(it)
                        }, {
                            Log.v("debug", "on error return")
                        })
                    }
            }
        } else {
            cachedRates
        }

    private val requestRatesObservable: Observable<List<RateModel>> = annaApiProvider.requestEuroRates().map {
        val result = it.cube.cubes.mapToRatesModelsList().toMutableList()
        result.add(0, RateModel("EUR", 1.0))

        result
    }
}