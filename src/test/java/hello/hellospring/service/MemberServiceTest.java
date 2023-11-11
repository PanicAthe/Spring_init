package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {  //의존 관계??? 해결하기 후에 복습 필수
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){     // Test 함수들 돌 때 마다 DB(레포지토리) 초기화
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {

        Member member = new Member();
        member.setName("ㄱㅇㅈ");

        long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {

        Member member1 = new Member();
        member1.setName("sprin");

        Member member2 = new Member();
        member2.setName("sprin");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

    /*   try{
            memberService.join(member2);
            fail(); //테스트 실패시 즉, 윗 줄에서 catch 하지 못하고 다음 줄로 이동했을 때
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
    }*/


    }

    @Test
    void findOne() {
    }
}