package com.example.currencyexchange

import dagger.Component

@Component(modules = arrayOf(AppModules::class))
interface AppComponent {
    fun inject(activity: CurrenciesScreen)
}