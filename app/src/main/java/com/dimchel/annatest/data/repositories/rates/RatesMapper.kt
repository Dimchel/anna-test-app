package com.dimchel.annatest.data.repositories.rates

import com.dimchel.annatest.data.api.schemes.RateScheme

fun RateScheme.mapToRatesModel() = RateModel(currency, rate)

fun List<RateScheme>.mapToRatesModelsList() =
    map { RateModel(it.currency, it.rate) }