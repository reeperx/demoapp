package com.togb.paki.ui.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.togb.paki.ui.data.PackingItem

class ListViewModel: ViewModel() {
    private val _privateList = mutableStateListOf<PackingItem>()

    val packingList: List<PackingItem> get() = _privateList

    init {
        _privateList.add(PackingItem("Hammer", "Tools", 5, "Easy to use"))
        _privateList.add(PackingItem("Watch", "Accessories", 1, "Too big"))
        _privateList.add(PackingItem("Printer", "Office Equipment", 9, "Best Brand"))
        _privateList.add(PackingItem("Oven", "Utensils", 2, "Colorful"))
    }

    fun addItem(item: PackingItem){
        _privateList.add(item)
    }

    fun getAllItems(): List<PackingItem> {
        return _privateList
    }

    fun filterTwoOrMore(): List<PackingItem>{
        return _privateList.filter { it.quantity >= 2 }
    }
}