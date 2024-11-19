package com.example.recyclerviewsqlitedemo
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1
        // Table and column names
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
    }
    override fun onCreate(db: SQLiteDatabase) {
        // Create the table
        val createTable = """
 CREATE TABLE $TABLE_USERS (
 $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
 $COLUMN_NAME TEXT NOT NULL,
 $COLUMN_AGE INTEGER NOT NULL
 )
 """.trimIndent()
        db.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop the existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }
    // Add a new user
    fun addUser(name: String, age: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
        }
        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result
    }
    // Retrieve all users
    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USERS"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age =
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                userList.add(User(id, name, age)) // Adjusted for id field
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }
    // Delete a user by ID (optional functionality)
    fun deleteUser(id: Int): Int {
        val db = writableDatabase
        val result = db.delete(TABLE_USERS, "$COLUMN_ID = ?",
            arrayOf(id.toString()))
        db.close()
        return result
    }
}