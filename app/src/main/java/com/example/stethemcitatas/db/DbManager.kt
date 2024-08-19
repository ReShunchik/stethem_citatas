package com.example.stethemcitatas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = dbHelper.writableDatabase
    }

    fun readDbData(): ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(DbName.TABLE_NAME, null, null,
            null, null, null, null)
        while (cursor?.moveToNext()!!) {
            val dataText = cursor.getString(1)
            dataList.add(dataText.toString());
        }
        cursor.close()
        return dataList
    }
}