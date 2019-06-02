package com.dimchel.annatest.features.exchange

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dimchel.annatest.data.repositories.rates.RateModel
import com.dimchel.annatest.data.repositories.rates.RatesRepository
import com.dimchel.annatest.di.AppScope
import io.reactivex.disposables.Disposable
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@AppScope
@InjectViewState
class ExchangePresenter @Inject constructor(
    private val ratesRepository: RatesRepository
) : MvpPresenter<ExchangeView>() {

    companion object {
        const val EXCHANGE_PRECISION = 2
    }

    private var ratesDisposable: Disposable? = null

    private lateinit var cachedRates: List<RateModel>
    private lateinit var currentInputRate: RateModel
    private lateinit var currentOutputRate: RateModel
    private var currentAmount: Double = 0.0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        requestRates()
    }

    override fun onDestroy() {
        ratesDisposable?.dispose()

        super.onDestroy()
    }

    // ===========================================================
    // ExchangeView
    // ===========================================================

    fun onInputCurrencySelected(currencyPosition: Int) {
        currentInputRate = cachedRates[currencyPosition]

        updateAmount()
    }

    fun onOutputCurrencySelected(currencyPosition: Int) {
        currentOutputRate = cachedRates[currencyPosition]

        updateAmount()
    }

    fun onAmountChanged(amount: Double) {
        currentAmount = amount

        updateAmount()
    }

    fun onRetryClicked() {
        requestRates()
    }

    fun onToAnimationClicked() {
        viewState.navigateToAnimationScreen()
    }

    // ===========================================================
    // Common
    // ===========================================================

    private fun updateAmount() {
        viewState.updateExchangedAmount(
            BigDecimal(currentAmount)
                .divide(BigDecimal(currentInputRate.rate), EXCHANGE_PRECISION, RoundingMode.HALF_UP)
                .multiply(BigDecimal(currentOutputRate.rate))
                .toDouble()
        )
    }

    private fun requestRates() {
        viewState.updateExchangeViewState(ExchangeViewState.LOADING)

        ratesDisposable = ratesRepository.getEuroRates().subscribe({
            cachedRates = it
            currentInputRate = cachedRates.first()
            currentOutputRate = cachedRates.first()

            viewState.updateExchangeViewState(ExchangeViewState.RATES)
            viewState.updateRatesList(cachedRates)

            ratesDisposable?.dispose()
        }, {
            viewState.updateExchangeViewState(ExchangeViewState.ERROR)

            ratesDisposable?.dispose()
        })
    }
}