package com.example.hello.hellospring.service;

import com.example.hello.hellospring.repository.JpaMemberRepository;
import com.example.hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration

//스프링에서 컨피그를 읽는다
/*
* @Service, @Autowired 등 어노테이션 넣어도 되지만, 정형화되지 않은 데이터베이스 레포지토리
* 스프링 빈으로 등록이 되어있어야 @Autowired 가 동작한다.
 */

public class SpringConfig {
    //JDBC 용 dataSource
    //JPA 사용을 위해서도 필요
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    //JPA를 위한 선언부

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


    //private final MemberRepository memberRepository;
    /*
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */
    @Bean
    //스프링 빈에 등록
    public MemberService memberService()
    {

        return new MemberService(memberRepository());
        //리포지토리 등록용
        //return new MemberService(memberRepository);
    }

    @Bean
    //리포지토리 등록
    public MemberRepository memberRepository()
    {
        return new JpaMemberRepository(em);
    }


}
