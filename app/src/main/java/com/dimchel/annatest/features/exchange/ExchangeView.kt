package com.dimchel.annatest.features.exchange

import com.arellomobile.mvp.MvpView
import com.dimchel.annatest.data.repositories.rates.RateModel

interface ExchangeView : MvpView {

    fun updateExchangeViewState(exchangeViewState: ExchangeViewState)

    fun updateRatesList(ratesList: List<RateModel>)
    fun updateExchangedAmount(exchangedAmount: Double)
}

enum class ExchangeViewState {
    LOADING,
    RATES,
    ERROR,
}