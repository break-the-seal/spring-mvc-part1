package io.brick.springmvc.domain.member

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MemberRepositoryTest {

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
        assertThat(findMember).isEqualTo(savedMember)
    }

    @Test
    fun findAll() {
        // given
        val member1 = Member(username = "member1", age = 20)
        val member2 = Member(username = "member2", age = 30)
        memberRepository.save(member1)
        memberRepository.save(member2)

        // when
        val result = memberRepository.findAll()

        // then
        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains(member1, member2)
    }
}