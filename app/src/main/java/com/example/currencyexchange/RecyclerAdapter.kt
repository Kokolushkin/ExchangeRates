package com.example.currencyexchange

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.currency_card.view.*
import java.util.*



class RecyclerAdapter(
    private var currencyResponse: CurrencyResponse,
    private var baseCurrencyAmount: Double,
    private val listener: RecyclerAdapterCallback,
    private val context: Context
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    private var rates: LinkedList<CurrencyItem>

    init {
        if (currencyResponse.base == null || currencyResponse.base == "EUR") {
            currencyResponse.rates!!["EUR"] = baseCurrencyAmount
        }
        rates = LinkedList(currencyResponse.rates!!.map { CurrencyItem(it.key, it.value) })
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.currency_card, viewGroup, false)
        return RecyclerAdapter(currencyResponse, baseCurrencyAmount, listener, context).ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, i: Int) {
        holder.bind(rates[i])
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    fun updateData(currencyResponse: CurrencyResponse) {
        rates.clear()
        val mRates = currencyResponse.rates
        val baseCurrencyItem = CurrencyItem(currencyResponse.base!!, currencyResponse.rates!![currencyResponse.base!!]!!)
        val newRates = ArrayList(mRates!!.map { CurrencyItem(it.key, it.value) })
        for (item in newRates) {
            if (item.name == currencyResponse.base) {
                newRates.remove(item)
                break
            }
        }
        rates.add(baseCurrencyItem)
        rates.addAll(newRates)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currencyItem: CurrencyItem) {
            val imageName = "flag_" + currencyItem.name.toLowerCase()
            val imageId = context.getResources().getIdentifier(imageName , "drawable", context.getPackageName())
            itemView.imageCurrency.setImageResource(imageId)
            itemView.nameCurrency.text = currencyItem.name
            itemView.valueRateCurrency.setText((currencyItem.rate * baseCurrencyAmount).toString())

            itemView.setOnClickListener {
                listener.onItemClicked(currencyItem)
            }
        }

    }
}

interface RecyclerAdapterCallback {
    fun onItemClicked(currencyItemData: CurrencyItem)
}