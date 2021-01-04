package com.example.dagboek.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dagboek.database.ItemDao
import com.example.dagboek.domain.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemRepository(private val itemDao: ItemDao) {

//    private val defaultItem = Item(-1, "Loading...", "One moment please")
//
//    private val items: MutableLiveData<List<Item>> =
//        MutableLiveData(listOf(defaultItem))

    private val items = MutableLiveData<List<Item>>()

//    private val _items = MutableLiveData<List<Item>>()

    fun getAll(): LiveData<List<Item>> {
        GlobalScope.launch {
            items.postValue(itemDao.getAll())

        }
        return items
    }

    fun getById(id: Int): LiveData<Item> {
//        val result = MutableLiveData(defaultItem)
        val result = MutableLiveData<Item>()
        GlobalScope.launch {
            result.postValue(itemDao.getById(id))
        }
        return result
    }

    fun add(item: Item) {
        GlobalScope.launch {
            // insert in db
            itemDao.insert(item)
            // update liveData
            getAll()

        }
    }
}