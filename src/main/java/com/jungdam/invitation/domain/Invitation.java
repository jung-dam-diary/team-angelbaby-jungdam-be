package com.jungdam.invitation.domain;

import com.jungdam.album.domain.Album;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.invitation.domain.vo.Status;
import com.jungdam.member.domain.Member;
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
public class Invitation extends BaseEntity {

    @Id
    @Column(name = "invitation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invitation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_member_id")
    private Member subjectMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_member_id")
    private Member targetMember;

    protected Invitation() {
    }

    public Invitation(Member targetMember, Member subjectMember) {
        this.targetMember = targetMember;
        this.subjectMember = subjectMember;
        this.status = Status.PENDING;
    }

    public void register(Album album) {
        this.album = album;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public boolean isAccept() {
        return Status.ACCEPT.equals(this.status);
    }

    public boolean isEquals(Member member) {
        return this.targetMember.equals(member);
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Album getAlbum() {
        return album;
    }

    public Member getSubjectMember() {
        return subjectMember;
    }

    public Member getTargetMember() {
        return targetMember;
    }
}