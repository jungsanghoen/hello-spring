package com.example.hello.hellospring.service;

import com.example.hello.hellospring.domain.Member;
import com.example.hello.hellospring.repository.MemberRepository;
import com.example.hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//서비스 클래스는 굉장히 비즈니스 적인 메소드 이름을 작성함.

//@Service
//어노테이션으로 바로 쓸 수 있지만 자바코드로 직접 스프링빈에 등록하는 방법 기술

//스프링이 관리시키기 위해 멤버 서비스로 입력하려면 @Service 어노테이션 입력해야ㅏㅁ.
public class MemberService {
    //repository 있어야함
    private final MemberRepository memberRepository;

 //  @Autowired
 //어노테이션으로 바로 쓸 수 있지만 자바코드로 직접 스프링빈에 등록하는 방법 기술
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    //회원가입

    /**
     * 회원 가입
     * 같은 이름 혹은 중복값 체크가 필요함
     */
    public Long join(Member member)
    {
        //같은 이름이 있는 중복 회원 X

        //ifPresent는 Null 이 아니면 동작을 하는 메소드
        //Optional로 감쌌기 때문에 완전 NULL이 아니라 Optional 객체가 되서 여러가지 메소드를 사용할 수 있음. NULL 체크 등
        //과거에는 if(result == NULL)로 코딩을 했었음.
        //NULL의 가능성이 있으면 오히려 Optional로 감싸서 반환을 함
        // 더 이쁜 코드는 아래와 같음
        /*
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 같은 이름이 존재합니다. getName 에러");
            });
        //위 자체가 어차피 Optional로 반환을 하기 때문
         */
        //그리고 위처럼 계속해서 나올거 같으면 Method로 빼는게 좋음 ctl + T하면 리팩토링 나옴
            validDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

    }
    //실제 위 메소드 검증이 필요하다. 즉 테스트 코드 작성 필요

    private void validDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 같은 이름이 존재합니다. getName 에러");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMemberAll()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberOnebyMemberId(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

}
