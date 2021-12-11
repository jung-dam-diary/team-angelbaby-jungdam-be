package com.jungdam.member.infrastructure;

import com.jungdam.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByOauthPermission(String oauthPermission);

    Optional<Member> findById(Long memberId);
}