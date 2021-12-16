package com.jungdam.diary.dto.response;

public class CheckBookmarkResponse {

    private final Long diaryId;
    private final boolean bookmark;

    public CheckBookmarkResponse(Long diaryId, boolean bookmark) {
        this.diaryId = diaryId;
        this.bookmark = bookmark;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public boolean isBookmark() {
        return bookmark;
    }
}