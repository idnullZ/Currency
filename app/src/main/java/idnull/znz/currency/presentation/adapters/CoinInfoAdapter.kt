package idnull.znz.currency.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import idnull.znz.currency.R
import idnull.znz.currency.domain.pojocoin.CoinPriceInfo
import idnull.znz.currency.presentation.coin.CoinFragment


class CoinInfoAdapter(private val context: CoinFragment):
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]

        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)


        holder.tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
        holder.tvPrice.text = coin.price
        holder.tvLastUpdate.text = String.format(lastUpdateTemplate, coin.getFormattedTime())

        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)


    }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.findViewById<ImageView>(R.id.ivLogoCoin)
        val tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdate)
    }


}
