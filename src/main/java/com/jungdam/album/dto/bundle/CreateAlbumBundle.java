package com.jungdam.album.dto.bundle;

import com.jungdam.album.domain.vo.FamilyMotto;
import com.jungdam.album.domain.vo.Thumbnail;
import com.jungdam.album.domain.vo.Title;
import com.jungdam.album.dto.request.CreateAlbumRequest;

public class CreateAlbumBundle {

    private final Long memberId;
    private final Title title;
    private final FamilyMotto familyMotto;
    private final Thumbnail thumbnail;

    public CreateAlbumBundle(Long memberId, CreateAlbumRequest request) {
        this.memberId = memberId;
        this.title = new Title(request.getTitle());
        this.familyMotto = new FamilyMotto(request.getFamilyMotto());
        this.thumbnail = new Thumbnail(request.getThumbnail());
    }

    public Long getMemberId() {
        return memberId;
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
