package com.example.stethemcitatas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stethemcitatas.R
import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDao
// Я теперь хз зачем я создал этот класс, позже надо будет его убарть, хватит и одного адаптера
abstract class GeneralQuoteAdapter(private var quoteList: List<Quote>, private val dao: QuoteDao):  RecyclerView.Adapter<GeneralQuoteAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote: TextView = itemView.findViewById(R.id.quote)
        val saved: CheckBox = itemView.findViewById(R.id.saved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount() = quoteList.size
}