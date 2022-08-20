package com.example.hello.hellospring.repository;

import com.example.hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Optional;

//데이터를 저장할 때 항상 transactional이 있어야함.
//데이터 변경 시 모든 데이터 변경이 transaction 안에서 해야함.
@Transactional
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    //JPA 는 EntityManger로 동작함

    //DI 함 Config에 있으므로 객체가 스프링에서 생성시키기 때문이다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        //insert 쿼리 알아서 만들어서 set id 함
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
         Member member = em.find(Member.class,id);
         return Optional.ofNullable(member);
         //em.find(조회할 타입과 식별자), PK일 때만 가능함
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
        //멤버 entity에 조회를 하는데, select *이 아니라 m 자체를 조회함.
    }
}
