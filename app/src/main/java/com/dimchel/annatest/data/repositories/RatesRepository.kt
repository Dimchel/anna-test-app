package com.dimchel.annatest.data.repositories

import com.dimchel.annatest.data.api.schemes.EuroRatesResponseScheme
import io.reactivex.Observable

interface RatesRepository {

    fun getEuroRates(): Observable<EuroRatesResponseScheme>

}