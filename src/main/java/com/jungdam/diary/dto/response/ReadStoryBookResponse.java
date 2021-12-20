package com.jungdam.diary.dto.response;

import java.time.LocalDate;

public class ReadStoryBookResponse {

    private final Long id;
    private final String title;
    private final LocalDate recordedAt;
    private final String photo;

    public ReadStoryBookResponse(Long id, String title, LocalDate recordedAt, String photo) {
        this.id = id;
        this.title = title;
        this.recordedAt = recordedAt;
        this.photo = photo;
    }

    public static ReadStoryBookResponseBuilder builder() {
        return new ReadStoryBookResponseBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getRecordedAt() {
        return recordedAt;
    }

    public String getPhoto() {
        return photo;
    }

    public static class ReadStoryBookResponseBuilder {

        private Long id;
        private String title;
        private LocalDate recordedAt;
        private String photo;

        private ReadStoryBookResponseBuilder() {
        }

        public ReadStoryBookResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ReadStoryBookResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public ReadStoryBookResponseBuilder recordedAt(LocalDate recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public ReadStoryBookResponseBuilder photo(final String photo) {
            this.photo = photo;
            return this;
        }

        public ReadStoryBookResponse build() {
            return new ReadStoryBookResponse(this.id, this.title, this.recordedAt, this.photo);
        }
    }

}
