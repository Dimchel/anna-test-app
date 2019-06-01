package com.dimchel.annatest.data.repositories

import com.dimchel.annatest.data.api.AnnaApiProvider
import com.dimchel.annatest.data.api.schemes.EuroRatesResponseScheme
import io.reactivex.Observable
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val annaApiProvider: AnnaApiProvider
) : RatesRepository {

    override fun getEuroRates(): Observable<EuroRatesResponseScheme> =
        annaApiProvider.requestEuroRates()

}