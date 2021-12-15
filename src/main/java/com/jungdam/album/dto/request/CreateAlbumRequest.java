package com.jungdam.album.dto.request;

public class CreateAlbumRequest {

    private final String title;
    private final String familyMotto;
    private final String thumbnail;

    public CreateAlbumRequest(String title, String familyMotto, String thumbnail) {
        this.title = title;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
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
}
