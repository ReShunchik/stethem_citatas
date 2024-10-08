package com.example.stethemcitatas.adapters

import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDao

class SavedQuoteAdapter(private var quoteList: List<Quote>, private val dao: QuoteDao) : GeneralQuoteAdapter(quoteList, dao) {

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val currentQuote = quoteList[position]
        holder.quote.text = currentQuote.quote
        if (currentQuote.isSaved == 1)
            holder.saved.isChecked = true

        holder.saved.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {                       // Если с цитаты снимают флажок
                currentQuote.isSaved = 0            // То надо у этой цитаты изменить
                dao.updateQuote(currentQuote)       // Этот параметр в бд
            }
        }
    }
}