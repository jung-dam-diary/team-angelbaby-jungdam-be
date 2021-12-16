package com.jungdam.album.dto.bundle;

import com.jungdam.album.domain.vo.FamilyMotto;
import com.jungdam.album.domain.vo.Thumbnail;
import com.jungdam.album.domain.vo.Title;
import com.jungdam.album.dto.request.UpdateAlbumRequest;

public class UpdateAlbumBundle {

    private final Long memberId;
    private final Long albumId;
    private final Title title;
    private final FamilyMotto familyMotto;
    private final Thumbnail thumbnail;

    public UpdateAlbumBundle(Long memberId, Long albumId, UpdateAlbumRequest request) {
        this.memberId = memberId;
        this.albumId = albumId;
        this.title = new Title(request.getTitle());
        this.familyMotto = new FamilyMotto(request.getFamilyMotto());
        this.thumbnail = new Thumbnail(request.getThumbnail());
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public Title getTitle() {
        return title;
    }

    public FamilyMotto getFamilyMotto() {
        return familyMotto;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
