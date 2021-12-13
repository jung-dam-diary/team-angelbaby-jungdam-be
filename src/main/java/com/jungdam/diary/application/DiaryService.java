package com.jungdam.diary.application;

import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.domain.vo.Title;
import com.jungdam.diary.infrastructure.DiaryRepository;
import com.jungdam.diary_photo.domain.DiaryPhoto;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.DuplicationException;
import com.jungdam.member.domain.Member;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Transactional
    public Diary save(Title title, Content content, RecordedAt recordedAt,
        List<DiaryPhoto> diaryPhotos, Member member) {
        if (existsByRecordedAtAndMember(recordedAt, member)) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_DIARY_RECORDED_AT);
        }
        Diary diary = Diary.builder()
            .title(title)
            .content(content)
            .recordedAt(recordedAt)
            .member(member)
            .build();

        diary.addDiaryPhotos(diaryPhotos);

        return diaryRepository.save(diary);
    }

    private boolean existsByRecordedAtAndMember(RecordedAt recordedAt, Member member) {
        return diaryRepository.existsByRecordedAtAndMember(recordedAt, member);
    }
}