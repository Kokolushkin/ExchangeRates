package com.example.currencyexchange

import android.databinding.ObservableDouble
import com.google.gson.annotations.SerializedName

data class CurrencyResponse (
    @SerializedName("base")
    val base: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("rates")
    val rates: MutableMap<String, Double>,

    val rate: Double
    ){
        var amount: ObservableDouble = ObservableDouble(100.0)
}