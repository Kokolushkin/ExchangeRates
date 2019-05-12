package com.example.currencyexchange

import rx.Observable

interface CurrenciesInteractor {
    fun getCurrencies(base: String?):
            Observable<CurrencyResponse>
}