package io.brick.springmvc.basic.requestmapping

import mu.KLogging
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mapping/users")
class MappingClassController {

    companion object: KLogging()

    /**
     * GET /mapping/users
     */
    @GetMapping
    fun users(): String? {
        return "get users"
    }

    /**
     * POST /mapping/users
     */
    @PostMapping
    fun addUser(): String? {
        return "post user"
    }

    /**
     * GET /mapping/users/{userId}
     */
    @GetMapping("/{userId}")
    fun findUser(@PathVariable userId: String): String? {
        return "get userId=$userId"
    }

    /**
     * PATCH /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    fun updateUser(@PathVariable userId: String): String? {
        return "update userId=$userId"
    }

    /**
     * DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: String): String? {
        return "delete userId=$userId"
    }
}