package com.jungdam.diary.dto.request;

import java.util.List;

public class UpdateDiaryRequest {

    private String diaryTitle;
    private String diaryContent;
    private List<String> diaryPhotos;

    protected UpdateDiaryRequest() {
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
}