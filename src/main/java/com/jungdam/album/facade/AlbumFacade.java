package com.jungdam.album.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.converter.AlbumConverter;
import com.jungdam.album.domain.Album;
import com.jungdam.album.dto.bundle.CreateAlbumBundle;
import com.jungdam.album.dto.response.CreateAlbumResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AlbumFacade {

    private final AlbumConverter albumConverter;
    private final AlbumService albumService;
    private final MemberService memberService;

    public AlbumFacade(AlbumConverter albumConverter, AlbumService albumService, MemberService memberService) {
        this.albumConverter = albumConverter;
        this.albumService = albumService;
        this.memberService = memberService;
    }

    @Transactional
    public CreateAlbumResponse insert(CreateAlbumBundle bundle) {
        Album album = albumConverter.toAlbum(bundle);
        Member member = memberService.findById(bundle.getMemberId());

        Album saveAlbum = albumService.save(album, member);

        return albumConverter.toCreateAlbumResponse(saveAlbum);
    }
}
