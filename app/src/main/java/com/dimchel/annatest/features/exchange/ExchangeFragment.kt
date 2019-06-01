package com.dimchel.annatest.features.exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dimchel.annatest.AnnaApp
import com.dimchel.annatest.R
import com.dimchel.annatest.common.visible
import com.dimchel.annatest.data.repositories.rates.RateModel
import kotlinx.android.synthetic.main.fragment_exchange.*
import javax.inject.Inject

class ExchangeFragment : MvpAppCompatFragment(), ExchangeView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ExchangePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var ratesAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        AnnaApp.appComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_exchange, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exchange_input_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onInputCurrencySelected(position)
            }
        }

        exchange_output_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.onOutputCurrencySelected(position)
            }
        }

        exchange_retry_button.setOnClickListener {
            presenter.onRetryClicked()
        }

        exchange_input_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) = Unit
            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onAmountChanged(text.toString().toDouble())
            }
        })
    }

    override fun updateExchangeViewState(exchangeViewState: ExchangeViewState) {
        when (exchangeViewState) {
            ExchangeViewState.LOADING -> {
                exchange_retry_button.visible = false
                exchange_view.visible = false
                exchange_progressbar.visible = true
            }
            ExchangeViewState.RATES -> {
                exchange_retry_button.visible = false
                exchange_progressbar.visible = false
                exchange_view.visible = true
            }
            ExchangeViewState.ERROR -> {
                exchange_progressbar.visible = false
                exchange_view.visible = false
                exchange_retry_button.visible = true
            }
        }
    }

    override fun updateRatesList(ratesList: List<RateModel>) {
        ratesAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_dropdown_item_1line,
            ratesList.map { it.currency }
        )
        ratesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        exchange_input_spinner.adapter = ratesAdapter
        exchange_output_spinner.adapter = ratesAdapter
    }
    override fun updateExchangedAmount(exchangedAmount: Double) {
        exchange_output_textview.text = exchangedAmount.toString()
    }
}