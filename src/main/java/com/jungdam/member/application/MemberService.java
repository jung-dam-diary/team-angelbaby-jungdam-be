package com.jungdam.member.application;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import com.jungdam.member.domain.Member;
import com.jungdam.member.dto.MemberBundle.MemberReadBundle;
import com.jungdam.member.dto.MemberResponse;
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
    public MemberResponse.MemberReadResponse find(MemberReadBundle bundle) {
        Member member = memberRepository.findByEmail(bundle.getEmail())
            .orElseThrow(() -> {
                throw new NotExistException(ErrorMessage.NOT_EXIST_MEMBER);
            });

        return new MemberResponse.MemberReadResponse(
            member.getEmail(),
            member.getNickname(),
            member.getAvatar(),
            member.getRole()
        );
    }
}