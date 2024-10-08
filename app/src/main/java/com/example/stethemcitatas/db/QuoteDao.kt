package com.example.stethemcitatas.db

import androidx.room.*

@Dao
interface QuoteDao {

    @Insert
    fun insert(quote: Quote)

    @Update
    fun updateQuote(quote: Quote)

    @Query("SELECT * FROM quotes WHERE id = :citataId ")
    fun get(citataId: Int): Quote

    @Query("SELECT * FROM quotes")
    fun getAll(): List<Quote>

    @Query("SELECT * FROM quotes WHERE saved = 1")
    fun getSaved(): List<Quote>

    @Query("SELECT * FROM quotes WHERE quote LIKE :searchQuery")
    fun getSearchQuotes(searchQuery: String): List<Quote>
}