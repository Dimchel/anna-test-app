package com.dimchel.annatest.data.repositories.rates

import io.reactivex.Observable

interface RatesRepository {

    fun getEuroRates(): Observable<List<RateModel>>

}