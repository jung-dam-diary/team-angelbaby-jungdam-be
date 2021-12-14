package com.jungdam.diary.application;

import com.jungdam.diary.convert.DiaryConverter;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.infrastructure.DiaryRepository;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.DuplicationException;
import com.jungdam.error.exception.NotExistException;
import com.jungdam.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryConverter diaryConverter;

    public DiaryService(DiaryRepository diaryRepository, DiaryConverter diaryConverter) {
        this.diaryRepository = diaryRepository;
        this.diaryConverter = diaryConverter;
    }

    @Transactional
    public Diary save(CreateDiaryBundle bundle, Member member) {
        if (existsByRecordedAtAndMember(bundle.getRecordedAt(), member)) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_DIARY_RECORDED_AT);
        }

        Diary diary = diaryConverter.toDiary(bundle, member);
        diary.addDiaryPhotos(bundle.getDiaryPhotos());

        return diaryRepository.save(diary);
    }

    private boolean existsByRecordedAtAndMember(RecordedAt recordedAt, Member member) {
        return diaryRepository.existsByRecordedAtAndMember(recordedAt, member);
    }

    @Transactional(readOnly = true)
    public Diary findById(Long id) {
        return diaryRepository.findById(id)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_DIARY));
    }
}