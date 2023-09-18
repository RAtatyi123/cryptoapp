package com.example.cryptoapp.presentation.coinlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.model.Coin
import com.example.cryptoapp.domain.use_case.CoinListUseCase
import com.example.cryptoapp.presentation.coin.CoinFragment
import com.squareup.picasso.Picasso

class CoinAdapter(private val context : Context,var coinList : ArrayList<Coin>) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(),Filterable {

    lateinit var  filteredList : ArrayList<Coin>

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
           val coinView = LayoutInflater.from(parent.context).inflate(R.layout.coin_recycler_view,parent,false)
            return CoinViewHolder(coinView)
        }

        override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
            val coin = coinList[position]
            holder.coinName.text = coin.name
            holder.coinLayout.setOnClickListener{
                val intent = Intent(context,CoinFragment::class.java)
                intent.putExtra("id",coin.id)
                context.startActivity(intent)
            }
            Picasso.get().load(coin.image).into(holder.coinImage)
        }

        override fun getItemCount(): Int {
            return coinList.size
        }

        fun setData(list: ArrayList<Coin>) {
            this.filteredList = list
            this.coinList = list
            notifyDataSetChanged()
        }

        inner class  CoinViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            var coinLayout: LinearLayout = view.findViewById(R.id.coinLinerLayout)
            val coinImage : ImageView = view.findViewById(R.id.imgCoinImage)
            val coinName : TextView = view.findViewById(R.id.txtCoinName)
        }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val string = constraint?.toString() ?: ""
                if (string.isNotEmpty()) {
                    var arrayList = arrayListOf<Coin>()
                    filteredList.filter {
                        it.name.lowercase().contains(string.lowercase())
                    }.forEach {
                        arrayList.add(it)
                    }
                    filteredList.clear()
                    filteredList.addAll(arrayList)
                }else{
                    filteredList = coinList
                }
                return FilterResults().apply {
                    this.values  = filteredList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values == null) {
                    ArrayList<Coin>()
                } else {
                    setData(filteredList)
                }
            }
        }
    }
}