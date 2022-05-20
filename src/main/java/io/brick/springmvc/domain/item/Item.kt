package io.brick.springmvc.domain.item

data class Item(
    var id: Long = 0,
    var itemName: String? = null,
    var price: Int? = 0,
    var quantity: Int? = 0
) {
    fun updateInfo(itemName: String, price: Int, quantity: Int) {
        this.itemName = itemName
        this.price = price
        this.quantity = quantity
    }
}
