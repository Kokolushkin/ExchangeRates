package com.example.currencyexchange

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CurrenciesScreen: MvpAppCompatActivity(), CurrenciesView, RecyclerAdapterCallback {
    override fun onItemClicked(currencyItem: CurrencyItem) {
        baseCurrency = currencyItem.name
        refreshData()
    }

    @InjectPresenter

    lateinit var currenciesPresenter: CurrenciesPresenter

    @Inject
    lateinit var interactor : CurrenciesInteractor

    @ProvidePresenter
    fun provideCurrenciesPresenter() : CurrenciesPresenter {
        return CurrenciesPresenter(interactor)
    }

    lateinit var recyclerAdapter : RecyclerAdapter

    var baseCurrency = "EUR"
    val baseCurrencyAmount = 1.0


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout.setOnRefreshListener {
            currenciesPresenter.refresh()
        }
        val horizontalLayoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = horizontalLayoutManager

        currenciesPresenter.loadCurrencies(baseCurrency)
    }

    private fun refreshData(){
        currenciesPresenter.loadCurrencies(baseCurrency)
    }
    override fun showProgress(){
        swipeRefreshLayout.isRefreshing = true
    }
    override fun showError(e: Throwable){
        Log.d("Tag", "ERROR ERROR ERROR ERROR")
        if(swipeRefreshLayout.isRefreshing)
            swipeRefreshLayout.isRefreshing = false
    }
    override fun showCurrencies(d: CurrencyResponse){
        updateitemAdapterData(d)
        if(swipeRefreshLayout.isRefreshing)
            swipeRefreshLayout.isRefreshing = false
    }
    override fun clearData(){}
    private fun updateitemAdapterData(d: CurrencyResponse){
        recyclerAdapter = RecyclerAdapter(d, baseCurrencyAmount, this, this)
        recyclerView.adapter = recyclerAdapter

        recyclerAdapter.updateData(d)
        recyclerAdapter.notifyDataSetChanged()

    }
}