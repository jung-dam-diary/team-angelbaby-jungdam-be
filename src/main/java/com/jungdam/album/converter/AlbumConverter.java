package com.jungdam.album.converter;

import com.jungdam.album.domain.Album;
import com.jungdam.album.dto.bundle.CreateAlbumBundle;
import com.jungdam.album.dto.response.CreateAlbumResponse;
import com.jungdam.album.dto.response.ReadOneAlbumResponse;
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

    public ReadOneAlbumResponse toReadOneAlbumResponseBuilder(Album album) {
        return ReadOneAlbumResponse.builder()
            .albumId(album.getId())
            .albumTitle(album.getTitleValue())
            .familyMotto(album.getFamilyMottoValue())
            .thumbnail(album.getThumbnailValue())
            .build();
    }
}