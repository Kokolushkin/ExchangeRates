package com.example.currencyexchange

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface CurrenciesApi {

    @GET("/latest")
    fun getCurrencies(@Query("base") base: String?):
            Observable<CurrencyResponse>
}