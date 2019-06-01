package com.dimchel.annatest.data.api

import com.dimchel.annatest.data.api.schemes.EuroRatesResponseScheme
import io.reactivex.Observable
import retrofit2.http.GET

interface AnnaService {

    @GET(ApiConstants.REQUEST_EURO_RATES)
    fun requestEuroRates(): Observable<EuroRatesResponseScheme>

}