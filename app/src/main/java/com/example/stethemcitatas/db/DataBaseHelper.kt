import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.stethemcitatas.db.DbName
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DataBaseHelper(private val context: Context) : SQLiteOpenHelper(context, DbName.DATABASE_NAME, null, DbName.DATABASE_VERSION) {

    private var db: SQLiteDatabase? = null
    private val dbPath = context.applicationInfo.dataDir + DbName.DATABASE_PATH + DbName.DATABASE_NAME

    override fun onCreate(db: SQLiteDatabase) {
        createDatabase()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DbName.TABLE_NAME}")
        onCreate(db)
    }

    @Throws(SQLiteException::class)
    fun openDatabase(): SQLiteDatabase {
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
        return db as SQLiteDatabase
    }

    @Synchronized
    override fun close() {
        db?.close()
        super.close()
    }

    @Throws(IOException::class)
    private fun createDatabase() {
        val dbExist = checkDatabase()
        if (!dbExist) {
            this.readableDatabase
            try {
                copyDatabase()
            } catch (e: IOException) {
                throw Error("Error copying database")
            }
        }
    }

    private fun checkDatabase(): Boolean {
        var checkDb: SQLiteDatabase? = null
        try {
            val dbPath = context.applicationInfo.dataDir + DbName.DATABASE_PATH + DbName.DATABASE_NAME
            checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            // Database does not exist yet
        }
        checkDb?.close()
        return checkDb != null
    }


    fun insertData(citata: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DbName.COLUMN_NAME_CITATA, citata)
        return db.insert(DbName.TABLE_NAME, null, contentValues)
    }

    fun getAllData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM ${DbName.TABLE_NAME}", null)
    }

    @Throws(IOException::class)
    private fun copyDatabase() {
        val dbPath: String = context.applicationInfo.dataDir + DbName.DATABASE_PATH + DbName.DATABASE_NAME
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
    }
}// чёт не коммититься