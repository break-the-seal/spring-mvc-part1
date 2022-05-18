package io.brick.springmvc.domain.item

data class Item(
    var id: Long = 0,
    var itemName: String?,
    var price: Int?,
    var quantity: Int?
) {
    fun updateInfo(itemName: String, price: Int, quantity: Int) {
        this.itemName = itemName
        this.price = price
        this.quantity = quantity
    }
}
