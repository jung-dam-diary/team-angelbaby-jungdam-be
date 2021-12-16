package com.jungdam.member.infrastructure;

import com.jungdam.member.domain.Member;
import com.jungdam.member.domain.vo.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByOauthPermission(String oauthPermission);

    @Override
    Optional<Member> findById(Long memberId);

    Optional<Member> findByEmail(Email email);
}