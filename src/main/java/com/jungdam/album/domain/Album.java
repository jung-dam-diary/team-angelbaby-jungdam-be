package com.jungdam.album.domain;

import com.jungdam.album.domain.vo.Diaries;
import com.jungdam.album.domain.vo.FamilyMotto;
import com.jungdam.album.domain.vo.Invitations;
import com.jungdam.album.domain.vo.Participants;
import com.jungdam.album.domain.vo.Thumbnail;
import com.jungdam.album.domain.vo.Title;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.participant.domain.Participant;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album extends BaseEntity {

    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private FamilyMotto familyMotto;

    @Embedded
    private Thumbnail thumbnail;

    @Embedded
    private Participants participants;

    @Embedded
    private Diaries diaries;

    @Embedded
    private Invitations invitations;

    protected Album() {

    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.register(this);
    }

    public void addDiary(Diary diary) {
        diaries.add(diary);
        diary.register(this);
    }

    public void addInvitation(Invitation invitation) {
        invitations.add(invitation);
        invitation.register(this);
    }

    public Long getId() {
        return id;
    }
}