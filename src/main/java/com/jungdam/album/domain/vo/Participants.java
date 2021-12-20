package com.jungdam.album.domain.vo;

import com.jungdam.album.domain.Album;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Participants {

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();

    public void add(Participant participant) {
        participants.add(participant);
    }

    public Participant find(Member member, Album album) {
        return participants.stream()
            .filter(p -> p.isEquals(member, album))
            .findFirst()
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT));
    }
}