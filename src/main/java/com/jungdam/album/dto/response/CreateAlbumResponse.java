package com.jungdam.album.dto.response;

public class CreateAlbumResponse {

    private final Long id;
    private final String title;
    private final String familyMotto;
    private final String thumbnail;

    public CreateAlbumResponse(Long id, String title, String familyMotto, String thumbnail) {
        this.id = id;
        this.title = title;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
    }

    public static CreateAlbumResponseBuilder builder() {
        return new CreateAlbumResponseBuilder();
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

    public static class CreateAlbumResponseBuilder {

        private Long id;
        private String title;
        private String familyMotto;
        private String thumbnail;

        private CreateAlbumResponseBuilder() {
        }

        public CreateAlbumResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public CreateAlbumResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public CreateAlbumResponseBuilder familyMotto(final String familyMotto) {
            this.familyMotto = familyMotto;
            return this;
        }

        public CreateAlbumResponseBuilder thumbnail(final String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public CreateAlbumResponse build() {
            return new CreateAlbumResponse(this.id, this.title, this.familyMotto, this.thumbnail);
        }
    }
}
