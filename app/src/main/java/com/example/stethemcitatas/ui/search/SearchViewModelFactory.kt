package com.example.stethemcitatas.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stethemcitatas.db.QuoteDao

class SearchViewModelFactory(private val dao: QuoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}