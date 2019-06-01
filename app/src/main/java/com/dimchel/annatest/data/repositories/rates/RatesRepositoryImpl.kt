package com.dimchel.annatest.data.repositories.rates

import com.dimchel.annatest.data.api.AnnaApiProvider
import io.reactivex.Observable
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val annaApiProvider: AnnaApiProvider
) : RatesRepository {

    override fun getEuroRates(): Observable<List<RateModel>> =
        annaApiProvider.requestEuroRates().map {
            it.cube.cubes.mapToRatesModelsList()
        }

}