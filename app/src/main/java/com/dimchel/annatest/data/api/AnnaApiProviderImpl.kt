package com.dimchel.annatest.data.api

import com.dimchel.annatest.common.AppSchedulers
import com.dimchel.annatest.data.api.schemes.EuroRatesResponseScheme
import com.dimchel.annatest.di.AppScope
import io.reactivex.Observable
import javax.inject.Inject

@AppScope
class AnnaApiProviderImpl @Inject constructor(
    private val appSchedulers: AppSchedulers,
    private val annaService: AnnaService
) : AnnaApiProvider {

    override fun requestEuroRates(): Observable<EuroRatesResponseScheme> =
        annaService.requestEuroRates()
            .subscribeOn(appSchedulers.io())
            .observeOn(appSchedulers.main())

}