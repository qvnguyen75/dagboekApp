package com.example.dagboek.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagboek.domain.Item
import com.example.dagboek.domain.ItemRepository

class ItemListVM(repository: ItemRepository) : ViewModel() {
    val allItems: LiveData<List<Item>> = repository.getAll()
}


class ItemListVMFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        // if this statement is true continue, else throw IllegalStateException
        check(modelClass.isAssignableFrom(ItemListVM::class.java))

        @Suppress("UNCHECKED_CAST")
        return ItemListVM(repository) as T
    }
}