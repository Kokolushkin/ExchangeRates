package com.example.currencyexchange

import rx.Observable


class CurrencyDataProviderImpl(private val api:
    CurrenciesApi): CurrencyDataProvider{

    override fun getCurrencies(base: String?):
            Observable<CurrencyResponse> {
        return api.getCurrencies(base)
    }
}