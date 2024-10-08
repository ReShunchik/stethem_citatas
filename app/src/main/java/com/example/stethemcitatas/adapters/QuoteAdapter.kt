package com.example.stethemcitatas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stethemcitatas.R
import com.example.stethemcitatas.adapters.GeneralQuoteAdapter.QuoteViewHolder
import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDao

class QuoteAdapter(private var quoteList: List<Quote>, private val dao: QuoteDao) : GeneralQuoteAdapter(quoteList, dao) {

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val currentQuote = quoteList[position]
        holder.quote.text = currentQuote.quote
        if (currentQuote.isSaved == 1)
            holder.saved.isChecked = true

        holder.saved.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                currentQuote.isSaved = 1
                dao.updateQuote(currentQuote)
            } else {
                currentQuote.isSaved = 0
                dao.updateQuote(currentQuote)
            }
        }
    }

    fun refreshList(newQuoteList: List<Quote>) {
        quoteList = newQuoteList
        notifyDataSetChanged()
    }
}