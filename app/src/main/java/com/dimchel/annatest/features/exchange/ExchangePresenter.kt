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
    private lateinit var inputRate: RateModel
    private lateinit var outputRate: RateModel

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
        inputRate = cachedRates[currencyPosition]
    }

    fun onOutputCurrencySelected(currencyPosition: Int) {
        outputRate = cachedRates[currencyPosition]
    }

    fun onAmountChanged(amount: Double) {
        viewState.updateExchangedAmount(
            BigDecimal(amount)
                .divide(BigDecimal(inputRate.rate), EXCHANGE_PRECISION, RoundingMode.HALF_UP)
                .multiply(BigDecimal(outputRate.rate))
                .toDouble()
        )
    }

    fun onRetryClicked() {
        requestRates()
    }

    // ===========================================================
    // Common
    // ===========================================================

    private fun requestRates() {
        viewState.updateExchangeViewState(ExchangeViewState.LOADING)

        ratesDisposable = ratesRepository.getEuroRates().subscribe({
            cachedRates = it
            inputRate = cachedRates.first()
            outputRate = cachedRates.first()

            viewState.updateExchangeViewState(ExchangeViewState.RATES)
            viewState.updateRatesList(cachedRates)
        }, {
            viewState.updateExchangeViewState(ExchangeViewState.ERROR)
        })
    }
}