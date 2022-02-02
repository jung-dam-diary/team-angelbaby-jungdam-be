package com.jungdam.album.domain.vo;

import com.jungdam.album.domain.Album;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
public class Participants {

    private final static Logger log = LoggerFactory.getLogger(Participants.class);

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();

    public void add(Participant participant) {
        participants.add(participant);
    }

    public Participant find(Member member, Album album) {
        return participants.stream()
            .filter(p -> p.isEquals(member, album))
            .findFirst()
            .orElseThrow(
                () -> new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT).error(log));
    }

    public Participant findById(Long id) {
        return participants.stream()
            .filter(p -> p.isEquals(id))
            .findFirst()
            .orElseThrow(
                () -> new NotExistException(ErrorMessage.NOT_EXIST_PARTICIPANT).error(log));
    }

    public boolean contains(Member member) {
        return participants.stream()
            .anyMatch(p -> p.isEquals(member));
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}