package com.jungdam.diary.infrastructure;

import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    boolean existsByRecordedAtAndMember(RecordedAt recordedAt, Member member);

    @Override
    Optional<Diary> findById(Long id);
}