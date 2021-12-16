package com.jungdam.album.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.converter.AlbumConverter;
import com.jungdam.album.domain.Album;
import com.jungdam.album.dto.bundle.CreateAlbumBundle;
import com.jungdam.album.dto.bundle.DeleteAlbumBundle;
import com.jungdam.album.dto.bundle.ReadOneAlbumBundle;
import com.jungdam.album.dto.bundle.UpdateAlbumBundle;
import com.jungdam.album.dto.response.CreateAlbumResponse;
import com.jungdam.album.dto.response.DeleteAlbumResponse;
import com.jungdam.album.dto.response.ReadOneAlbumResponse;
import com.jungdam.album.dto.response.UpdateAlbumResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AlbumFacade {

    private final AlbumConverter albumConverter;
    private final AlbumService albumService;
    private final MemberService memberService;
    private final ParticipantService participantService;

    public AlbumFacade(AlbumConverter albumConverter,
        AlbumService albumService, MemberService memberService,
        ParticipantService participantService) {
        this.albumConverter = albumConverter;
        this.albumService = albumService;
        this.memberService = memberService;
        this.participantService = participantService;
    }

    @Transactional
    public CreateAlbumResponse insert(CreateAlbumBundle bundle) {
        Album album = albumConverter.toAlbum(bundle);
        Member member = memberService.findById(bundle.getMemberId());

        Album saveAlbum = albumService.save(album, member);

        return albumConverter.toCreateAlbumResponse(saveAlbum);
    }

    @Transactional(readOnly = true)
    public ReadOneAlbumResponse find(ReadOneAlbumBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkNotExists(album, member);

        return albumConverter.toReadOneAlbumResponse(album);
    }

    @Transactional
    public DeleteAlbumResponse delete(DeleteAlbumBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkMemberIsOwnerRole(album, member);

        albumService.delete(album);

        return albumConverter.toDeleteAlbumResponse(album, member);
    }

    @Transactional
    public UpdateAlbumResponse update(UpdateAlbumBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkMemberIsOwnerRole(album, member);

        album.update(
            bundle.getTitle(),
            bundle.getFamilyMotto(),
            bundle.getThumbnail()
        );

        return albumConverter.toUpdateAlbumResponse(album);
    }
}