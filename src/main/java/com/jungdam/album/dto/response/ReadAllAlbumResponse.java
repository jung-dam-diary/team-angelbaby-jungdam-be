package com.jungdam.album.dto.response;

public class ReadAllAlbumResponse {

    private final Long id;
    private final String title;
    private final String familyMotto;
    private final String thumbnail;

    public ReadAllAlbumResponse(Long id, String title, String familyMotto, String thumbnail) {
        this.id = id;
        this.title = title;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
    }

    public static ReadAllAlbumResponseBuilder builder() {
        return new ReadAllAlbumResponseBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFamilyMotto() {
        return familyMotto;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public static class ReadAllAlbumResponseBuilder {

        private Long id;
        private String title;
        private String familyMotto;
        private String thumbnail;

        private ReadAllAlbumResponseBuilder() {
        }

        public ReadAllAlbumResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ReadAllAlbumResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public ReadAllAlbumResponseBuilder familyMotto(final String familyMotto) {
            this.familyMotto = familyMotto;
            return this;
        }

        public ReadAllAlbumResponseBuilder thumbnail(final String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public ReadAllAlbumResponse build() {
            return new ReadAllAlbumResponse(this.id, this.title, this.familyMotto, this.thumbnail);
        }
    }
}
