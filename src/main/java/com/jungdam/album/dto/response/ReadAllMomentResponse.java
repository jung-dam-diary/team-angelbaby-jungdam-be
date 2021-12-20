package com.jungdam.album.dto.response;

import com.jungdam.diary.domain.Diary;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReadAllMomentResponse {

    private static final String EMPTY_STRING = "";

    private final List<Moment> moments;
    private final Boolean hasNext;

    public ReadAllMomentResponse(
        List<Diary> diaries, Boolean hasNext) {
        this.hasNext = hasNext;
        this.moments = diaries.stream()
            .map(d -> {
                String thumbnail = d.getDiaryPhotosValue().stream()
                    .findFirst()
                    .orElse(EMPTY_STRING);
                return Moment.builder()
                    .diaryId(d.getId())
                    .title(d.getTitleValue())
                    .thumbnail(thumbnail)
                    .recordedAt(d.getRecordedAtValue())
                    .build();
            })
            .collect(Collectors.toList());
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public List<Moment> getMoments() {
        return moments;
    }

    private static class Moment {

        private final Long diaryId;
        private final String title;
        private final String thumbnail;
        private final LocalDate recordedAt;

        public Moment(Long diaryId, String title, String thumbnail,
            LocalDate recordedAt) {
            this.diaryId = diaryId;
            this.title = title;
            this.thumbnail = thumbnail;
            this.recordedAt = recordedAt;
        }

        public static MomentBuilder builder() {
            return new MomentBuilder();
        }

        public Long getDiaryId() {
            return diaryId;
        }

        public String getTitle() {
            return title;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public LocalDate getRecordedAt() {
            return recordedAt;
        }

        public static class MomentBuilder {

            private Long diaryId;
            private String title;
            private String thumbnail;
            private LocalDate recordedAt;

            private MomentBuilder() {
            }

            public MomentBuilder diaryId(final Long diaryId) {
                this.diaryId = diaryId;
                return this;
            }

            public MomentBuilder title(final String title) {
                this.title = title;
                return this;
            }

            public MomentBuilder thumbnail(final String thumbnail) {
                this.thumbnail = thumbnail;
                return this;
            }

            public MomentBuilder recordedAt(final LocalDate recordedAt) {
                this.recordedAt = recordedAt;
                return this;
            }

            public Moment build() {
                return new Moment(diaryId, title, thumbnail, recordedAt);
            }
        }
    }

}

