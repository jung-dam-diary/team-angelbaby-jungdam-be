package com.jungdam.auth.application;

import com.jungdam.auth.domain.AuthPrincipal;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.member.infrastructure.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(
        MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Member member = memberRepository.findByEmail(new Email(username))
            .orElseThrow(() -> {
                throw new NotExistException(ErrorMessage.NOT_EXIST_MEMBER);
            });
        return AuthPrincipal.create(member);
    }
}