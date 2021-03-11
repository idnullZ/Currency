package idnull.znz.currency.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import idnull.znz.currency.R
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo
import idnull.znz.currency.domain.pojocoin.dataUsd.ItemRates

class RatesAdapter : RecyclerView.Adapter<RatesAdapter.IRates>() {
    var ratesList: List<ItemRates> = listOf()
    fun setData(rates: List<ItemRates>) {
        ratesList = rates
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IRates {
        return IRates(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rates, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IRates, position: Int) {

        holder.nameSymbols.text = ratesList[position].name
        holder.nameCost.text = ratesList[position].cost.toString()

    }

    override fun getItemCount(): Int = ratesList.size

    inner class IRates(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameSymbols = itemView.findViewById<TextView>(R.id.symbols)
        val nameCost = itemView.findViewById<TextView>(R.id.cost)


    }
}