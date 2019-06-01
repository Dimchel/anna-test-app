package com.dimchel.annatest.data.api

import com.dimchel.annatest.data.api.schemes.EuroRatesResponseScheme
import io.reactivex.Observable

interface AnnaApiProvider {

    fun requestEuroRates(): Observable<EuroRatesResponseScheme>

}