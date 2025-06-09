package com.togb.paki.ui.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    private val _packingList = mutableStateListOf<PackingItem>()

    val packingList: List<PackingItem> get() = _packingList

    init {
        _packingList.add(PackingItem("Sun Glasses", "Accessories", 5, "Comfortable for travel"))
        _packingList.add(PackingItem("Shorts", "Clothing", 1, "Essential for hygiene"))
        _packingList.add(PackingItem("Computer", "Electronics", 2, "Essential for working"))
        _packingList.add(PackingItem("Hammer", "Tools", 1, "Don’t forget this!"))
    }

    fun addItem(item: PackingItem) {
        _packingList.add(item)
    }

    fun getAllItems(): List<PackingItem> {
        return _packingList
    }

    fun getItemsTwoOrMore(): List<PackingItem> {
        return _packingList.filter { it.quantity >= 2 }
    }
}