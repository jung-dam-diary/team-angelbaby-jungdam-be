package com.jungdam.invitation.converter;

import com.jungdam.invitation.domain.Invitation;
import com.jungdam.invitation.dto.response.CreateInvitationResponse;
import org.springframework.stereotype.Component;

@Component
public class InvitationConverter {

    public CreateInvitationResponse toCreateInvitationResponse(Invitation invitation) {
        return new CreateInvitationResponse(invitation.getId());
    }

}
