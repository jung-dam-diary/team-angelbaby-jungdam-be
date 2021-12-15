package com.jungdam.participant.converter;

import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.response.ReadAllParticipant;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter {

    public ReadAllParticipantResponse toReadAllParticipantResponse(List<Participant> participants) {
        List<ReadAllParticipant> readParticipantResponses = participants.stream()
            .map(p ->
                ReadAllParticipant.builder()
                    .participantEmail(p.getMember().getEmailValue())
                    .participantNickname(p.getNickname())
                    .participantAvatar(p.getMember().getAvatarValue())
                    .participantRole(p.getRole().name())
                    .build())
            .collect(Collectors.toList());

        return new ReadAllParticipantResponse(readParticipantResponses);
    }
}
