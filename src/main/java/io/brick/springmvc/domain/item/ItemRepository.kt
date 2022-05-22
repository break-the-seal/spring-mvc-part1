package io.brick.springmvc.domain.item

import org.springframework.stereotype.Repository

@Repository
class ItemRepository {

    companion object {
        val store: MutableMap<Long, Item> = mutableMapOf()
        var sequence: Long = 0L
    }

    fun save(item: Item): Item {
        item.id = ++sequence
        store[item.id] = item

        return item
    }

    fun findById(id: Long): Item? {
        return store[id]
    }

    fun findAll(): List<Item> {
        return store.values.toList()
    }

    fun update(itemId: Long, updateParam: Item) {
        val findItem = findById(itemId)
            ?: throw RuntimeException("Item[id=${itemId}] Not Found")

        findItem.updateInfo(
            itemName = updateParam.itemName!!,
            price = updateParam.price!!,
            quantity = updateParam.quantity!!
        )
    }

    fun clearStore() {
        store.clear()
    }
}