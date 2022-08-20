package com.example.hello.hellospring.service;

import com.example.hello.hellospring.domain.Member;
import com.example.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//테스트가 필요한 원본 class에서 Ctrl + Shift + T 하면 테스트클래스가 자동으로 생성됨.
//똑같이 테스트 코드는 동시성이 발생하므로 memberJoin 시 에러가 발생하게 됨.
//클리어를 해줘야함
//단, MemberMemoryRepository를 가져와서 클리어 해야함.
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beferEach()
    {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach()
    {
        memberRepository.clearStore();
    }
    //메모리가 클리어 됨.

    @Test
    void 회원가입() {
        //테스트 코드는 한글로 작성해도 됨.
        //빌드 시 테스트 코드는 포함되지 않암.

        //given
        //이게 주어졌을 때
        Member member = new Member();
        member.setName("spring1");

        //when
        //이걸 실행하면
        Long saveId = memberService.join(member);

        //then
        //이게 나와야 해
        Member findMember =  memberService.findMemberOnebyMemberId(saveId).get();

        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

        //테스트는 예외 Flow가 훨씬 중요하다
    }

    @Test
    public void 중복회원예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

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