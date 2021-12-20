package com.jungdam.diary.application;

import com.jungdam.album.domain.Album;
import com.jungdam.album.dto.response.ReadAllMomentResponse;
import com.jungdam.diary.converter.DiaryConverter;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.Bookmark;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.response.ReadAllFeedDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllStoryBookResponse;
import com.jungdam.diary.infrastructure.DiaryRepository;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.common.DuplicationException;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.participant.domain.Participant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class DiaryService {

    private final static int DEFAULT_PAGE = 0;
    private final static String NOT_EXISTS_NEXT_DIARY = "NOT_EXISTS_NEXT_DIARY";


    private final DiaryRepository diaryRepository;
    private final DiaryConverter diaryConverter;

    public DiaryService(DiaryRepository diaryRepository, DiaryConverter diaryConverter) {
        this.diaryRepository = diaryRepository;
        this.diaryConverter = diaryConverter;
    }

    @Transactional
    public Diary save(CreateDiaryBundle bundle, Participant participant) {
        if (existsByRecordedAtAndParticipant(bundle.getRecordedAt(), participant)) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_DIARY_RECORDED_AT);
        }

        Diary diary = diaryConverter.toDiary(bundle, participant);
        diary.addDiaryPhotos(bundle.getDiaryPhotos());

        return diaryRepository.save(diary);
    }

    private boolean existsByRecordedAtAndParticipant(RecordedAt recordedAt,
        Participant participant) {
        return diaryRepository.existsByRecordedAtAndParticipant(recordedAt, participant);
    }

    @Transactional(readOnly = true)
    public Diary findById(Long id) {
        return diaryRepository.findById(id)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_DIARY));
    }

    @Transactional(readOnly = true)
    public ReadAllMomentResponse findByAlbumAndBookmarkByCursor(Album album, Bookmark bookmark,
        Long cursorId, Pageable page) {
        final List<Diary> diaries = findByAlbumAndBookmark(album, bookmark,
            cursorId, page);

        if (diaries.isEmpty()) {
            return new ReadAllMomentResponse(diaries, false);
        }
        final Diary lastDiaryOfList = diaries.get(diaries.size() - 1);

        return new ReadAllMomentResponse(diaries,
            hasNext(album, bookmark, lastDiaryOfList.getId()));
    }

    private Boolean hasNext(Album album, Bookmark bookmark, Long cursorId) {
        return diaryRepository.existsByAlbumAndBookmarkAndIdLessThan(album, bookmark, cursorId);
    }

    private List<Diary> findByAlbumAndBookmark(Album album, Bookmark bookmark, Long cursorId,
        Pageable page) {
        if (Objects.isNull(cursorId)) {
            return diaryRepository.findAllByAlbumAndBookmarkOrderByIdDesc(album, bookmark, page);
        }
        return diaryRepository.findAllByAlbumAndBookmarkAndIdLessThanOrderByIdDesc(album, bookmark,
            cursorId, page);
    }

    // TODO : 리펙토링 필요
    @Transactional(readOnly = true)
    public ReadAllFeedDiaryResponse findAllFeed(Album album, String cursorId, int size) {
        final List<Diary> diaries = findByAlbumAndRecordedAt(album, cursorId,
            pageSetup(size));

        if (diaries.isEmpty()) {
            return diaryConverter.toReadAllFeedDiaryResponse(false, NOT_EXISTS_NEXT_DIARY,
                diaries);
        }

        final Diary diary = diaries.get(diaries.size() - 1);

        RecordedAt recordedAt = diary.getRecordedAt();

        return makeReadAllFeedDiaryResponse(album, diaries, recordedAt);
    }

    private ReadAllFeedDiaryResponse makeReadAllFeedDiaryResponse(Album album,
        List<Diary> diaries, RecordedAt recordedAt) {
        if (hasNext(album, recordedAt)) {
            return diaryConverter.toReadAllFeedDiaryResponse(true,
                recordedAt.getRecordedAt().toString(),
                diaries);
        }
        return diaryConverter.toReadAllFeedDiaryResponse(false, NOT_EXISTS_NEXT_DIARY,
            diaries);
    }

    private Boolean hasNext(Album album, RecordedAt recordedAt) {
        return diaryRepository.existsByAlbumAndRecordedAtLessThan(album, recordedAt);
    }

    private List<Diary> findByAlbumAndRecordedAt(Album album, String recordedAt,
        Pageable pageable) {
        if (!StringUtils.hasText(recordedAt)) {
            return findAllByAlbumOrderByRecordedAtDesc(album, pageable);
        }
        RecordedAt date = new RecordedAt(LocalDate.parse(recordedAt, DateTimeFormatter.ISO_DATE));
        return findAllByAlbumAndRecordedAtLessThanOrderByRecordedAtDesc(album, date, pageable);
    }

    private List<Diary> findAllByAlbumOrderByRecordedAtDesc(Album album, Pageable pageable) {
        return diaryRepository.findAllByAlbumOrderByRecordedAtDesc(album, pageable);
    }

    private List<Diary> findAllByAlbumAndRecordedAtLessThanOrderByRecordedAtDesc(Album album,
        RecordedAt recordedAt, Pageable pageable) {
        return diaryRepository.findAllByAlbumAndRecordedAtLessThanOrderByRecordedAtDesc(album,
            recordedAt, pageable);
    }

    private Pageable pageSetup(int size) {
        return PageRequest.of(DEFAULT_PAGE, size);
    }

    //TODO Id 기준이 아닌 RecordedAt 기준으로 변경 필요
    public ReadAllStoryBookResponse findAllStoryBook(Album album, Participant participant,
        Long cursorId, Pageable pageable) {
        final List<Diary> diaries = findByAlbumAndParticipant(album, participant, cursorId,
            pageable);

        final Long lastIdOfList =
            diaries.isEmpty() ? null : diaries.get(diaries.size() - 1).getId();

        return diaryConverter.toReadAllStoryBookResponse(
            hasNextStoryBook(album, participant, lastIdOfList),
            participant, diaries);
    }

    private List<Diary> findByAlbumAndParticipant(Album album, Participant participant,
        Long cursorId,
        Pageable pageable) {
        if (Objects.isNull(cursorId)) {
            return diaryRepository
                .findAllByAlbumAndParticipantOrderByIdDesc(album, participant, pageable);
        } else {
            return diaryRepository
                .findAllByAlbumAndParticipantAndIdLessThanOrderByIdDesc(album, participant,
                    cursorId, pageable);
        }
    }

    private Boolean hasNextStoryBook(Album album, Participant participant, Long lastIdOfList) {
        if (Objects.isNull(lastIdOfList)) {
            return false;
        }
        return diaryRepository.existsByAlbumAndParticipantAndIdLessThan(album, participant,
            lastIdOfList);
    }
}