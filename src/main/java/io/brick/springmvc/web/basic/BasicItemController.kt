package io.brick.springmvc.web.basic

import io.brick.springmvc.domain.item.Item
import io.brick.springmvc.domain.item.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct

@Controller
@RequestMapping("/basic/items")
class BasicItemController(
    private val itemRepository: ItemRepository
) {
    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model.addAttribute("items", items)
        return "basic/items"
    }

    @GetMapping("/{itemId}")
    fun item(@PathVariable("itemId") itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)

        return "basic/item"
    }

    @GetMapping("/add")
    fun addForm(): String {
        return "basic/addForm"
    }

//    @PostMapping("/add")
    fun addItemV1(
        @RequestParam("itemName") itemName: String,
        @RequestParam("price") price: Int,
        @RequestParam("quantity") quantity: Int,
        model: Model
    ): String {
        val item = Item().apply {
            this.itemName = itemName
            this.price = price
            this.quantity = quantity
        }

        itemRepository.save(item)

        model.addAttribute("item", item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV2(
        @ModelAttribute("item") item: Item,
        model: Model
    ): String {
        itemRepository.save(item)

        // @ModelAttribute -> model에 자동으로 설정을 해준다. (설정한 이름으로)
//        model.addAttribute("item", item)

        return "basic/item"
    }

//    @PostMapping("/add")
    fun addItemV3(
        @ModelAttribute item: Item,
        model: Model
    ): String {
        itemRepository.save(item)

        // 클래스명의 첫번째 대문자를 소문자로 바꾼 이름으로 model 설정
        // (Item -> item)
        return "basic/item"
    }

    // @ModelAttribute 아예 생략 가능
    @PostMapping("/add")
    fun addItemV4(
        item: Item,
        model: Model
    ): String {
        itemRepository.save(item)

        // PRG (POST - Redirect - GET)
        return "redirect:/basic/items/${item.id}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(@PathVariable("itemId") itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun edit(@PathVariable("itemId") itemId: Long, @ModelAttribute item: Item): String {
        itemRepository.update(itemId, item)

        // @PathVariable에 지정한 itemId를 그대로 사용 가능
        return "redirect:/basic/items/{itemId}"
    }

    @PostConstruct
    fun init() {
        itemRepository.save(Item(itemName = "itemA", price = 10000, quantity = 10))
        itemRepository.save(Item(itemName = "itemB", price = 20000, quantity = 20))
    }
}