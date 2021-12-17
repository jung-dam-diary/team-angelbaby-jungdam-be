package com.jungdam.participant.domain;

import com.jungdam.album.domain.Album;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.vo.Comments;
import com.jungdam.participant.domain.vo.Diaries;
import com.jungdam.participant.domain.vo.Emojis;
import com.jungdam.participant.domain.vo.Nickname;
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
    private Nickname nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @JoinColumn(name = "diaries_id")
    private Diaries diaries;

    @JoinColumn(name = "comment_id")
    private Comments comments;

    @JoinColumn(name = "emojis_id")
    private Emojis emojis;

    protected Participant() {
    }

    public Participant(Member member, Role role) {
        this.role = role;
        this.nickname = new Nickname(member.getNicknameValue());
        this.member = member;
    }

    public void updateNickname(Nickname nickname) {
        this.nickname = nickname;
    }

    public boolean isEquals(Member member, Album album) {
        return this.member.equals(member) && this.album.equals(album);
    }

    public Long getId() {
        return id;
    }

    public String getRoleValue() {
        return role.getRole();
    }

    public String getNicknameValue() {
        return nickname.getNickname();
    }

    public String getMemberAvatar() {
        return member.getAvatarValue();
    }

    public Role getRole() {
        return role;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Member getMember() {
        return member;
    }

    public Album getAlbum() {
        return album;
    }

    public void register(Album album) {
        this.album = album;
    }
}