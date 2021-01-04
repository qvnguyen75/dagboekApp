package com.example.dagboek

import android.app.Application
import com.example.dagboek.database.ItemDatabase
import com.example.dagboek.domain.ItemRepository

class MyApplication: Application() {
    private val db by lazy { ItemDatabase.getInstance(this) }
    val itemRepo by lazy { ItemRepository(db.itemDao()) }
}