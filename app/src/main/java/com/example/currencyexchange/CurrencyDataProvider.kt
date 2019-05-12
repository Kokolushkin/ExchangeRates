package com.example.currencyexchange

import rx.Observable

interface CurrencyDataProvider {
    fun getCurrencies(base: String?):
            Observable<CurrencyResponse>
}