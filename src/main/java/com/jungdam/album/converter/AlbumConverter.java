package com.jungdam.album.converter;

import com.jungdam.album.domain.Album;
import com.jungdam.album.dto.bundle.CreateAlbumBundle;
import com.jungdam.album.dto.response.CreateAlbumResponse;
import com.jungdam.album.dto.response.DeleteAlbumResponse;
import com.jungdam.album.dto.response.ReadOneAlbumResponse;
import com.jungdam.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class AlbumConverter {

    public Album toAlbum(CreateAlbumBundle bundle) {
        return Album.builder()
            .title(bundle.getTitle())
            .familyMotto(bundle.getFamilyMotto())
            .thumbnail(bundle.getThumbnail())
            .build();
    }

    public CreateAlbumResponse toCreateAlbumResponse(Album album) {
        return CreateAlbumResponse.builder()
            .id(album.getId())
            .title(album.getTitleValue())
            .familyMotto(album.getFamilyMottoValue())
            .thumbnail(album.getThumbnailValue())
            .build();
    }

    public ReadOneAlbumResponse toReadOneAlbumResponse(Album album) {
        return ReadOneAlbumResponse.builder()
            .id(album.getId())
            .title(album.getTitleValue())
            .familyMotto(album.getFamilyMottoValue())
            .thumbnail(album.getThumbnailValue())
            .build();
    }

    public DeleteAlbumResponse toDeleteAlbumResponse(Album album, Member member) {
        return new DeleteAlbumResponse(album.getId(), member.getId());
    }
}