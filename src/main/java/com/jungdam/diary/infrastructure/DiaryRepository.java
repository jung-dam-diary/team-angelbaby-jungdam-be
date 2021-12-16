package com.jungdam.diary.infrastructure;

import com.jungdam.album.domain.Album;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.Bookmark;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    boolean existsByRecordedAtAndMember(RecordedAt recordedAt, Member member);

    @Override
    Optional<Diary> findById(Long id);

    List<Diary> findAllByAlbumAndBookmark(Album album, Bookmark bookmark);
}