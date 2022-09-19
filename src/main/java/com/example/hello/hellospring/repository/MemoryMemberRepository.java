package com.example.hello.hellospring.repository;

import com.example.hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//인터페이스 구현체
//@Repository
//어노테이션으로 바로 쓸 수 있지만 자바코드로 직접 스프링빈에 등록하는 방법 기술
@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //메모리 임시 저장소 DB라고 표현할 수 있음
    // 실무에서는 공유변수인 경우 hashmap은 동시성 문제가 발생할 수 있어서 concurrency hash map 을 사용해야 함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {

        //store에 삽입 전 sequence 값 자동 증가
        member.setId(++sequence);
        store.put(member.getId(), member);
            return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //Null 반환 가능성이 있음 그러면 Optional로 감싸서 반환해야함. 그럼 클라이언트에서 뭔가 할 수 있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
        //return Optional.empty();
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //루프로 돌리는 람다
        //필터로 파라미터로 넘어온 name과 member의 name이 같은지 비교
        //그냥 찾으면 반환
        //findany는 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //실무는 리스트 많이 씀
    }

    public void clearStore()
    {
        store.clear();
    }
}
