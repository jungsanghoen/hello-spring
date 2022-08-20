package com.example.hello.hellospring.repository;

import com.example.hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스 상속받으려면 extends
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository  {
    @Override
    //JpaRepository가 선언되어 있으면 구현체를 자동으로 스프링 JPA가 만들어줌
    //Config에서 가져다 쓰면 됨
    Optional<Member> findByName(String name);
    //Optional<Member> findByEmail(String email);
    //규칙이 있음. findByName 으로 하면
    //자동으로  JPL select m from Member m where m.name =? 으로 만들어짐.
    //인터페이스 이름만으로 끝남.
}
