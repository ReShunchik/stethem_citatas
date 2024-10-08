package com.hfad.tasks
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stethemcitatas.db.QuoteDao
import com.example.stethemcitatas.ui.home.HomeViewModel

class HomeViewModelFactory(private val dao: QuoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}