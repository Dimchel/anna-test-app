package com.dimchel.annatest

import com.dimchel.annatest.data.repositories.rates.RateModel
import com.dimchel.annatest.data.repositories.rates.RatesRepository
import com.dimchel.annatest.features.exchange.ExchangePresenter
import com.dimchel.annatest.features.exchange.ExchangeView
import com.dimchel.annatest.features.exchange.ExchangeViewState
import com.dimchel.annatest.features.exchange.`ExchangeView$$State`
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class ExchangePresenterTest {

    private val ratesRepository = mock(RatesRepository::class.java)
    private val view = mock(ExchangeView::class.java)

    private lateinit var presenter: ExchangePresenter
    private lateinit var viewState: `ExchangeView$$State`

    private val testRatesList: List<RateModel> = listOf(RateModel("EUR", 3.4))

    @BeforeEach
    fun setUp() {
        viewState = mock(`ExchangeView$$State`::class.java)

        presenter = ExchangePresenter(ratesRepository)
        presenter.setViewState(viewState)
    }

    @Test
    fun `Should show loader from start`() {
        successfulStart()

        verify(viewState).updateExchangeViewState(ExchangeViewState.LOADING)
    }

    @Test
    fun `Should show rates state after success request`() {
        successfulStart()

        verify(viewState).updateExchangeViewState(ExchangeViewState.RATES)
        verify(viewState).updateRatesList(testRatesList)
    }

    @Test
    fun `Should show error state after failure request`() {
        whenever(ratesRepository.getEuroRates()).thenReturn(Observable.error(Exception()))
        presenter.attachView(view)

        verify(viewState).updateExchangeViewState(ExchangeViewState.LOADING)
        verify(viewState).updateExchangeViewState(ExchangeViewState.ERROR)
    }

    @Test
    fun `Should update exchanged amount after input currency selected`() {
        successfulStart()

        presenter.onInputCurrencySelected(0)

        verify(viewState).updateExchangedAmount(0.0)
    }

    @Test
    fun `Should update exchanged amount after output currency selected`() {
        successfulStart()

        presenter.onOutputCurrencySelected(0)

        verify(viewState).updateExchangedAmount(0.0)
    }

    @Test
    fun `Should update exchanged amount amount changing`() {
        successfulStart()

        presenter.onAmountChanged(0.0)

        verify(viewState).updateExchangedAmount(0.0)
    }

    @Test
    fun `Should call navigation after to animation clicked`() {
        successfulStart()

        presenter.onToAnimationClicked()

        verify(viewState).navigateToAnimationScreen()
    }

    // ===========================================================
    // Common
    // ===========================================================

    private fun successfulStart() {
        whenever(ratesRepository.getEuroRates()).thenReturn(Observable.just(testRatesList))

        presenter.attachView(view)
    }
}