package com.jungdam.member.application;

import com.jungdam.member.domain.Member;
import com.jungdam.member.dto.bundle.ReadMemberBundle;
import com.jungdam.member.dto.response.ReadMemberResponse;
import com.jungdam.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public ReadMemberResponse find(ReadMemberBundle bundle) {
        Member member = memberRepository.findByOauthPermission(bundle.getOauthPermission());

        return new ReadMemberResponse(
            member.getEmail(),
            member.getNickname(),
            member.getAvatar(),
            member.getRole()
        );
    }
}