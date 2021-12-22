package com.jungdam.participant.converter;

import com.jungdam.album.domain.Album;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.response.CheckParticipantResponse;
import com.jungdam.participant.dto.response.ReadAllParticipant;
import com.jungdam.participant.dto.response.ReadAllParticipantResponse;
import com.jungdam.participant.dto.response.UpdateNicknameParticipantResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter {

    public ReadAllParticipantResponse toReadAllParticipantResponse(List<Participant> participants) {
        List<ReadAllParticipant> readAllParticipants = participants.stream()
            .map(this::toReadAllParticipant)
            .collect(Collectors.toList());

        return new ReadAllParticipantResponse(readAllParticipants);
    }

    private ReadAllParticipant toReadAllParticipant(Participant participant) {
        Member member = participant.getMember();

        return ReadAllParticipant.builder()
            .email(member.getEmailValue())
            .nickname(participant.getNicknameValue())
            .avatar(member.getAvatarValue())
            .role(participant.getRoleValue())
            .build();
    }

    public CheckParticipantResponse toCheckParticipantResponse(Album album, boolean existence) {
        return new CheckParticipantResponse(album.getId(), existence);
    }

    public UpdateNicknameParticipantResponse toUpdateNicknameParticipantResponse(
        Participant participant) {
        return new UpdateNicknameParticipantResponse(participant.getId(),
            participant.getNicknameValue());
    }
}