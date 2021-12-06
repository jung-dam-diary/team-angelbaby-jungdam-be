package com.jungdam.album.domain;

import com.jungdam.album.domain.vo.Diaries;
import com.jungdam.album.domain.vo.Invitations;
import com.jungdam.album.domain.vo.Participants;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Album extends BaseEntity {

    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_title", length = 20)
    private String title;

    @Column(name = "album_family_motto", length = 30)
    private String familyMotto;

    @Column(name = "album_thumbnail")
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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
}