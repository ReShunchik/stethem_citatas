package com.example.stethemcitatas.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import com.example.stethemcitatas.R
import java.io.InputStream
import java.io.OutputStream

class DbHelper(context: Context) : SQLiteOpenHelper(
    context, DbName.DATABASE_NAME, null, DbName.DATABASE_VERSION
) {
    //private val dbPath: String = context.applicationInfo.dataDir + DbName.DATABASE_PATH + DbName.DATABASE_NAME

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbName.CREATE_TABLE)
        //copyDatabase()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DbName.SQL_DELELE_TABLE)
        onCreate(db)
    }

    /*@Throws(IOException::class)
    private fun copyDatabase() {
        val inputStream: InputStream = context.assets.open(DbName.DATABASE_NAME)
        val outputFilePath = dbPath
        val outputStream: OutputStream = FileOutputStream(outputFilePath)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }*/
}