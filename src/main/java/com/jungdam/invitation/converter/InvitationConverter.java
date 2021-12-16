package com.jungdam.invitation.converter;

import com.jungdam.album.domain.Album;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.dto.response.CreateInvitationResponse;
import com.jungdam.invitation.dto.response.ReadAllInvitationResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class InvitationConverter {

    public CreateInvitationResponse toCreateInvitationResponse(Invitation invitation) {
        return new CreateInvitationResponse(invitation.getId());
    }

    public List<ReadAllInvitationResponse> toReadAllInvitationResponse(List<Invitation> invitationList) {
        return invitationList.stream()
            .map(invitation -> {
                Album album = invitation.getAlbum();
                return ReadAllInvitationResponse.builder()
                    .invitationId(invitation.getId())
                    .invitationCreatedAt(invitation.getCreatedAt())
                    .albumTitle(album.getTitleValue())
                    .build();
            }).collect(Collectors.toList());
    }
}
