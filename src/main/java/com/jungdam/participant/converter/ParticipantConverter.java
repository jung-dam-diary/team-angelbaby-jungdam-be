package com.jungdam.participant.converter;

import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import com.jungdam.participant.dto.response.ReadParticipantResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter {

    public ReadAllParticipantResponse toReadAllParticipantResponse(List<Participant> participants) {
        List<ReadParticipantResponse> readParticipantResponses = participants.stream().map(p ->
            ReadParticipantResponse.builder()
                .participantEmail(p.getMember().getEmail())
                .participantNickname(p.getNickname())
                .participantAvatar(p.getMember().getAvatar())
                .participantRole(p.getRole().name())
                .build()
        ).collect(Collectors.toList());

        return new ReadAllParticipantResponse(readParticipantResponses);
    }
}
