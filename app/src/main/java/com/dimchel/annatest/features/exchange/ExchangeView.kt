package com.dimchel.annatest.features.exchange

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dimchel.annatest.data.repositories.rates.RateModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface ExchangeView : MvpView {

    fun updateExchangeViewState(exchangeViewState: ExchangeViewState)

    fun updateRatesList(ratesList: List<RateModel>)
    fun updateExchangedAmount(exchangedAmount: Double)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToAnimationScreen()
}

enum class ExchangeViewState {
    LOADING,
    RATES,
    ERROR,
}