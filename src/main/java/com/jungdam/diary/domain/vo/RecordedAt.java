package com.jungdam.diary.domain.vo;

import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.InvalidArgumentException;
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
        validate(recordedAt);
        this.recordedAt = recordedAt;
    }

    private void validate(LocalDate recordedAt) {
        if (recordedAt.isAfter(LocalDate.now())) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_RECORDED_AT);
        }
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }
}