package com.example.stethemcitatas.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stethemcitatas.db.Quote
import com.example.stethemcitatas.db.QuoteDao
import kotlinx.coroutines.launch

class HomeViewModel(private val dao: QuoteDao) : ViewModel() {
    val fiveQuotes = arrayListOf<Quote>()
    init {
        refreshQuotes()
    }

    fun refreshQuotes(){
        val randoms = arrayListOf<Int>()
        for(i in 0..4)
            randoms.add((0..317).random())
        fiveQuotes.clear()
        randoms.forEach {
            fiveQuotes.add(dao.get(it))
        }
    }
}