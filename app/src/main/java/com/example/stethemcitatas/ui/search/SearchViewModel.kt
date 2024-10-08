package com.example.stethemcitatas.ui.search

import androidx.lifecycle.ViewModel
import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDao

class SearchViewModel(private val dao: QuoteDao) : ViewModel() {
    val searchQuotes = arrayListOf<Quote>()

    init{
        searchQuotes.addAll(dao.getAll())
    }

    fun makeSearch(searchString: StringBuilder){
        searchQuotes.clear()
        val searchResults = dao.getSearchQuotes(searchString.toString())
        searchQuotes.addAll(searchResults)
    }
}