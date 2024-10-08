package com.example.stethemcitatas.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDataBase : RoomDatabase() {

    abstract fun QuoteDao(): QuoteDao

    companion object {
        fun getInstance(context: Context): QuoteDataBase {
            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDataBase::class.java,
                    "stetQuotes"
                    ).allowMainThreadQueries()
                    .createFromAsset("databases/stetQuotes.db")
                    .build()
                }
            }
        }
}