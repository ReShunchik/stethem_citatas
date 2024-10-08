package com.example.stethemcitatas.ui.saved

import androidx.lifecycle.ViewModel
import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDao

class SavedViewModel(private val dao: QuoteDao) : ViewModel() {
    val savedQuotes = arrayListOf<Quote>()

    init {
        savedQuotes.addAll(dao.getSaved())
    }
}