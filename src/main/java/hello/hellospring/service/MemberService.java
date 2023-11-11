package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemoryMemberRepository memberRepository;
    
    public MemberService(MemoryMemberRepository memberRepository) {
        //new를 써서 repo을 생성하게 하지 말고 생성자에 인수로 받아서 공유하도록
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){   //회원 가입

        validateDuplicateMember(member); //중복 회원 방지
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
