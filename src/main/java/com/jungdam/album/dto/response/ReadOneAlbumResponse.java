package com.jungdam.album.dto.response;

public class ReadOneAlbumResponse {

    private final Long albumId;
    private final String albumTitle;
    private final String familyMotto;
    private final String thumbnail;

    public ReadOneAlbumResponse(Long albumId, String albumTitle, String familyMotto,
        String thumbnail) {
        this.albumId = albumId;
        this.albumTitle = albumTitle;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
    }

    public static ReadOneAlbumResponseBuilder builder() {
        return new ReadOneAlbumResponseBuilder();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public String getFamilyMotto() {
        return familyMotto;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public static class ReadOneAlbumResponseBuilder {

        private Long albumId;
        private String albumTitle;
        private String familyMotto;
        private String thumbnail;

        private ReadOneAlbumResponseBuilder() {
        }

        public ReadOneAlbumResponseBuilder albumId(final Long albumId) {
            this.albumId = albumId;
            return this;
        }

        public ReadOneAlbumResponseBuilder albumTitle(final String albumTitle) {
            this.albumTitle = albumTitle;
            return this;
        }

        public ReadOneAlbumResponseBuilder familyMotto(final String familyMotto) {
            this.familyMotto = familyMotto;
            return this;
        }

        public ReadOneAlbumResponseBuilder thumbnail(final String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public ReadOneAlbumResponse build() {
            return new ReadOneAlbumResponse(this.albumId, this.albumTitle, this.familyMotto,
                this.thumbnail);
        }
    }
}