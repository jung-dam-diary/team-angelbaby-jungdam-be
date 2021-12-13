package com.jungdam.diary.domain.vo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecordedAt {

    @Column(name = "diary_recorded_at")
    private LocalDate recordedAt;

    protected RecordedAt() {

    }

    public RecordedAt(LocalDate recordedAt) {
        this.recordedAt = recordedAt;
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }
}