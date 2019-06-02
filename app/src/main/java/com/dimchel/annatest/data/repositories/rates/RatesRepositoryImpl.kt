package com.dimchel.annatest.data.repositories.rates

import com.dimchel.annatest.data.api.AnnaApiProvider
import io.reactivex.Observable
import javax.inject.Inject

class RatesRepositoryImpl @Inject constructor(
    private val annaApiProvider: AnnaApiProvider
) : RatesRepository {

    override fun getEuroRates(): Observable<List<RateModel>> =
        annaApiProvider.requestEuroRates().map {
            val result = it.cube.cubes.mapToRatesModelsList().toMutableList()
            result.add(0, RateModel("EUR", 1.0))

            result
        }
}