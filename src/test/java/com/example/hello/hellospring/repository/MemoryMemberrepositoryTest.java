package com.example.hello.hellospring.repository;

import com.example.hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

//관례 상 테스트할 class 명의 뒤에 test를 붙임
//굳이 public으로 하지 않음
//Test의 각각의 Method는 순서대로 실행되지 않음. 그러므로 테스트 케이스에 따라 원하는 결과가 나오지 않을 가능성이 있음
//깔끔하게 클리어 해줘야 함.
class MemoryMemberrepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //Method 하나씩 실행될때 마다 끝나고 동작하는 어노테이션
    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }
    //save 기능 테스트
    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        //실제 기능 테스트 시작
        Member result = repository.findById(member.getId()).get();

        //검증부, 똑같으면 참임
        //1.단순히 같은지 System.out.println 으로도 볼 수가 있음
        //System.out.println("result = " + (result.getName() == member.getName()));

        //시스템 구문으로 보는게 아니라 assertions 기능을 통해 봄, Junit 라이브러리임
        //Assertions.assertEquals(member, result);
        //(기대값, 결과값)
        //위 assertions 보다 더 좋은 기능이 있음, Junit 라이브러리가 아니라, assertj 라이브러리 이용하면 아래와 같은 구문
        assertThat(member).isEqualTo(result);
        //alt + enter를 이용해서 assertThat Method는 static Import를 하여 바로 method를 사용할 수 있음
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result =  repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
