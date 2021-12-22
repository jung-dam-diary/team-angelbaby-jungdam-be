package com.jungdam.diary.infrastructure;

import com.jungdam.album.domain.Album;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.Bookmark;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.participant.domain.Participant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    boolean existsByRecordedAtAndParticipant(RecordedAt recordedAt, Participant participant);

    @Override
    Optional<Diary> findById(Long id);

    Boolean existsByAlbumAndBookmarkAndIdLessThan(Album album, Bookmark bookmark, Long id);

    List<Diary> findAllByAlbumOrderByRecordedAtDesc(Album album, Pageable page);

    List<Diary> findAllByAlbumAndRecordedAtLessThanOrderByRecordedAtDesc(Album album,
        RecordedAt recordedAt, Pageable page);

    Boolean existsByAlbumAndRecordedAtLessThan(Album album, RecordedAt recordedAt);

    List<Diary> findAllByAlbumAndParticipantOrderByIdDesc(Album album, Participant participant,
        Pageable pageable);

    List<Diary> findAllByAlbumAndParticipantAndIdLessThanOrderByIdDesc(
        Album album, Participant participant, Long cursorId, Pageable pageable);

    Boolean existsByAlbumAndParticipantAndIdLessThan(Album album, Participant participant,
        Long cursorId);

    List<Diary> findAllByAlbumAndBookmarkOrderByRecordedAtDesc(Album album, Bookmark bookmark,
        Pageable page);

    List<Diary> findAllByAlbumAndBookmarkAndIdLessThanOrderByRecordedAtDesc(Album album,
        Bookmark bookmark, Long cursorId,
        Pageable page);
}