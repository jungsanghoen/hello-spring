package com.example.hello.hellospring.controller;

import com.example.hello.hellospring.domain.Member;
import com.example.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller

public class MemberController {
    private final MemberService memberService;
    // 이렇게 하면 다른컨트롤러에서 가져다가 쓸 수 없다.
    //차라리 스프링 컨테이너에 등록을 하면 된다.

    @Autowired
    //스프링 빈 멤버 서비스들을 연결시켜야 함.
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
        System.out.println("member service = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm()
    {
        return "members/createMemberForm";
    }

    //Form에서 Post 액션 시 호출됨. Form의 key와 동일한 클래스를 만들면
    //스프링이 맵핑해줌.
    @PostMapping("/members/new")
    public String create(MemberForm form)
    {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members = memberService.findMemberAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
