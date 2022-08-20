package com.example.hello.hellospring.service;

import com.example.hello.hellospring.domain.Member;
import com.example.hello.hellospring.repository.JdbcMemberRepository;
import com.example.hello.hellospring.repository.MemberRepository;
import com.example.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

//스프링과 엮어서 통합 테스트
//DB 까지 테스트 가능

//
// DB는 transaction이 있어서 tr 후 커밋해야함. auto commit 아니면 커밋하지 않으면 반영 안됨
// Transactional 어노테이션을 달면 트랜잭션 실행 후 커밋 안하고 롤백 해버림. 그래서 반복 테스트하도록 됨
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //after each, before each 필요 없음


    @Test
    @DisplayName("하이욤_이름 변경했어여")
    //@Commit은 실제 디비에 commit
    void 회원가입() {
        //테스트 코드는 한글로 작성해도 됨.
        //빌드 시 테스트 코드는 포함되지 않암.

        //given
        //이게 주어졌을 때
        Member member = new Member();
        member.setName("spring1_중복회원");

        //when
        //이걸 실행하면
        Long saveId = memberService.join(member);

        //then
        //이게 나와야 해
        Member findMember =  memberRepository.findById(saveId).get();

        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

        //테스트는 예외 Flow가 훨씬 중요하다
    }

    @Test
    public void 중복회원예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("spring2_중복회원");

        Member member2 = new Member();
        member2.setName("spring2_중복회원");

        //when

        memberService.join(member1);

        /*
        try {
            memberService.join(member2);
            //예외가 안발생하면 fail
            fail("예외가 발생해야 합니다. 예외가 실패");
        } catch (IllegalStateException e)
        {
            //org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 같은 이름이 존재합니다. getName 에러");
            System.out.println("에러 발생 " + e.getMessage());
        }
        //예외 발생됨
        //try catch로 잡을 수도 있음.
        //단, 더 좋은 문법이 있음 assertThrows()
        */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //기대값 작성, 그리고 람다의 해당 메소드 실행 시 같으면 에러 던지고 아니면 테스트 통과
        //에러가 발생하고 있으므로 테스트 통과
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 같은 이름이 존재합니다. getName 에러");
        //then
    }

    @Test
    void findMemberAll() {
    }

    @Test
    void findMemberOnebyMemberId() {
    }
}