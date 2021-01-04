package com.example.dagboek.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagboek.domain.Item
import com.example.dagboek.domain.ItemRepository

class ItemDetailVM(repository: ItemRepository, itemId: Int) : ViewModel() {
    val item: LiveData<Item> = repository.getById(itemId)
}

class ItemDetailVMFactory(private val repository: ItemRepository, private val itemId: Int) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        check(modelClass.isAssignableFrom(ItemDetailVM::class.java))

        return ItemDetailVM(repository, itemId) as T
    }
}