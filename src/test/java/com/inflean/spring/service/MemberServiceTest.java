package com.inflean.spring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import com.inflean.spring.domain.Member;
import com.inflean.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트가 시작할 때마다 같은 저장소를 쓰기 위해 의존성을 주입을 하는 콜백 메소드
    @BeforeEach
    public void beforeEach(){
        memberRepository =  new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트가 끝날 때마다 저장소의 데이터를 초기화하기 위한 콜백 메소드
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();

    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring2");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 모든회원을_조회한다() {
    }

    @Test
    void 회원1명을_조회한다() {
    }
}