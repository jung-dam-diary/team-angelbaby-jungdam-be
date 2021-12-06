package com.jungdam.album.domain.vo;

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
}
