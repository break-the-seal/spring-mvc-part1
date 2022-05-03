package io.brick.springmvc.domain.member

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MemberRepositoryTest {
    private val memberRepository = MemberRepository.getInstance()

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun save() {
        // given
        val member = Member(username = "hello", age = 20)

        // when
        val savedMember = memberRepository.save(member)

        // then
        val findMember = memberRepository.findById(savedMember.id)
        assertEquals(findMember, savedMember)
    }

    @Test
    fun findAll() {
        // given
        val member1 = Member(username = "member1", age = 20)
        val member2 = Member(username = "member2", age = 20)

        memberRepository.save(member1)
        memberRepository.save(member2)

        // when
        val result = memberRepository.findAll()

        // then
        assertEquals(result.size, 2)
        assertTrue(result.contains(member1))
        assertTrue(result.contains(member2))
    }
}