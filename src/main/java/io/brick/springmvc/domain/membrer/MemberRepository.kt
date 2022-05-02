package io.brick.springmvc.domain.membrer

class MemberRepository private constructor(){
    companion object {
        private val store: MutableMap<Long, Member> = mutableMapOf()
        private var sequence = 0L

        private val instance = MemberRepository()

        fun getInstance(): MemberRepository {
            return instance
        }
    }

    fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id] = member

        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}