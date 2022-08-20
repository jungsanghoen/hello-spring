package com.example.hello.hellospring.repository;

import com.example.hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//DB가 선정되지 않아서 DB에 접근하기 위한 인터페이스 선언

public interface MemberRepository {
    Member save(Member member);
    // 멤버 저장
    Optional<Member> findById(Long id);
    //Null 반환을 위해 JAVA 8 에서 적용된 Optional 적용
    Optional<Member> findByName(String name);
    //Null 반환을 위해 JAVA 8 에서 적용된 Optional 적용

    List<Member> findAll();

}
