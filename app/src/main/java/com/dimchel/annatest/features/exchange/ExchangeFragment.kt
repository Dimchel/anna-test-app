package com.dimchel.annatest.features.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.dimchel.annatest.AnnaApp
import com.dimchel.annatest.R
import javax.inject.Inject

class ExchangeFragment : MvpAppCompatFragment(), ExchangeView {

    @Inject
    @InjectPresenter
    lateinit var presenter: ExchangePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

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


    }
}