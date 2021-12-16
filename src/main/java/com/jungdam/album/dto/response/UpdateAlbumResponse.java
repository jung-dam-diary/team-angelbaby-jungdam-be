package com.jungdam.album.dto.response;


public class UpdateAlbumResponse {

    private final Long id;
    private final String title;
    private final String familyMotto;
    private final String thumbnail;

    public UpdateAlbumResponse(Long id, String title, String familyMotto, String thumbnail) {
        this.id = id;
        this.title = title;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
    }

    public static UpdateAlbumResponseBuilder builder() {
        return new UpdateAlbumResponseBuilder();
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

    public static class UpdateAlbumResponseBuilder {

        private Long id;
        private String title;
        private String familyMotto;
        private String thumbnail;

        private UpdateAlbumResponseBuilder() {
        }

        public UpdateAlbumResponse.UpdateAlbumResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public UpdateAlbumResponse.UpdateAlbumResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public UpdateAlbumResponse.UpdateAlbumResponseBuilder familyMotto(
            final String familyMotto) {
            this.familyMotto = familyMotto;
            return this;
        }

        public UpdateAlbumResponse.UpdateAlbumResponseBuilder thumbnail(final String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public UpdateAlbumResponse build() {
            return new UpdateAlbumResponse(this.id, this.title, this.familyMotto, this.thumbnail);
        }
    }
}
