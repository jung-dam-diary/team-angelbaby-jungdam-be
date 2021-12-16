package com.jungdam.participant.converter;

import com.jungdam.album.domain.Album;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.response.CheckParticipantResponse;
import com.jungdam.participant.dto.response.ReadAllParticipant;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter {

    public ReadAllParticipantResponse toReadAllParticipantResponse(List<Participant> participants) {
        List<ReadAllParticipant> readAllParticipants = participants.stream()
            .map(participant ->
                ReadAllParticipant.builder()
                    .email(participant.getMember().getEmailValue())
                    .nickname(participant.getNicknameValue())
                    .avatar(participant.getMember().getAvatarValue())
                    .role(participant.getRoleValue())
                    .build())
            .collect(Collectors.toList());

        return new ReadAllParticipantResponse(readAllParticipants);
    }

    public CheckParticipantResponse toCheckParticipantResponse(Album album, boolean existence) {
        return new CheckParticipantResponse(album.getId(), existence);
    }
}