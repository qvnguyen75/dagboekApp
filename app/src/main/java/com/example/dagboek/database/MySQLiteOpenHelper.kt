package com.example.dagboek.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


private const val DB_NAME = "my_db"
private const val DB_VERSION = 1
const val ITEM_TABLE_NAME = "items"

class MySQLiteOpenHelper(context: Context) : SQLiteOpenHelper(
    context, DB_NAME, null, DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.apply {
            // delete
            execSQL("DROP TABLE IF EXISTS $ITEM_TABLE_NAME")

            // create
            execSQL("CREATE TABLE $ITEM_TABLE_NAME (" +
                    " id INT PRIMARY KEY NOT NULL, " +
                    " title TEXT NOT NULL, " +
                    " body TEXT NOT NULL)")

            // append
            execSQL("""INSERT INTO $ITEM_TABLE_NAME VALUES 
            (1, "post 1", "body post 1"),
            (2, "post 2", "body post 2"),
            (3, "post 3", "body post 3")""".trimMargin())
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion < newVersion) {
            onCreate(db)
        }
    }
}