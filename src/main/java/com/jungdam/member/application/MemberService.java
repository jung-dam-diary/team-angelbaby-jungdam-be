package com.jungdam.member.application;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.member.converter.MemberConverter;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.dto.bundle.ReadMemberBundle;
import com.jungdam.member.dto.bundle.UpdateMemberBundle;
import com.jungdam.member.dto.response.ReadMemberResponse;
import com.jungdam.member.dto.response.UpdateMemberResponse;
import com.jungdam.member.infrastructure.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    public MemberService(MemberRepository memberRepository,
        MemberConverter memberConverter) {
        this.memberRepository = memberRepository;
        this.memberConverter = memberConverter;
    }

    @Transactional(readOnly = true)
    public ReadMemberResponse find(ReadMemberBundle bundle) {
        Member member = findById(bundle.getMemberId());

        return memberConverter.toReadMemberResponse(member);
    }

    @Transactional(readOnly = true)
    public Member findByEmailForSearch(Email email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public UpdateMemberResponse update(UpdateMemberBundle bundle) {
        Member member = findById(bundle.getMemberId());
        member.update(bundle.getNickname(), bundle.getAvatar());

        return memberConverter.toUpdateMemberResponse(member);
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_MEMBER));
    }
}