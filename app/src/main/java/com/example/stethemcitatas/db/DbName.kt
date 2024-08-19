package com.example.stethemcitatas.db

import android.provider.BaseColumns

object DbName : BaseColumns {
    const val DATABASE_PATH = "/databases/"

    const val TABLE_NAME = "citatas"
    const val COLUMN_NAME_CITATA = "citata"
    const val COLUMN_NAME_SAVED = "saved"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "ALLCitatas.db"
    const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME(" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_NAME_CITATA TEXT,$COLUMN_NAME_SAVED INTEGER)"
    const val SQL_DELELE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}