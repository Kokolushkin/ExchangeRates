package com.example.currencyexchange

import com.arellomobile.mvp.MvpView

interface CurrenciesView: MvpView {

    fun showProgress()
    fun showError(e: Throwable)
    fun showCurrencies(viewData: CurrencyResponse)
    fun clearData()
}