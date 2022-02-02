package com.jungdam.diary.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
public class RecordedAt {

    private final static Logger log = LoggerFactory.getLogger(RecordedAt.class);

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
            throw new InvalidArgumentException(ErrorMessage.INVALID_DIARY_RECORDED_AT).error(log);
        }
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordedAt that = (RecordedAt) o;
        return Objects.equals(recordedAt, that.recordedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordedAt);
    }
}