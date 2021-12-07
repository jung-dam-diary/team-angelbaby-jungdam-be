package com.jungdam.diary.domain.vo;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecordedAt {

    @Column(name = "diary_recorded_at")
    private LocalDateTime recordedAt;

    protected RecordedAt() {

    }

    public RecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}