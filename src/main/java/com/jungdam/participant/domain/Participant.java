package com.jungdam.participant.domain;

import com.jungdam.album.domain.Album;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.vo.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Participant extends BaseEntity {

    @Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "participant_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "participant_nickname")
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    protected Participant() {
    }

    public Participant(Member member) {
        this.role = Role.OWNER;
        this.nickname = member.getNicknameValue();
        this.member = member;
    }

    public void register(Album album) {
        this.album = album;
    }
}