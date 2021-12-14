package com.jungdam.diary.dto.request;

import java.time.LocalDate;
import java.util.List;

public class CreateDiaryRequest {

    private String diaryTitle;
    private String diaryContent;
    private List<String> diaryPhotos;
    private LocalDate recordedAt;

    protected CreateDiaryRequest() {
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public List<String> getDiaryPhotos() {
        return diaryPhotos;
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }
}