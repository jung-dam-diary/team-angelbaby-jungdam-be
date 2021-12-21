package com.jungdam.comment.domain;

import com.jungdam.comment.domain.vo.Content;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.participant.domain.Participant;
import java.util.Objects;
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
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    protected Comment() {
    }

    public Comment(Content content, Participant participant) {
        this.content = content;
        this.participant = participant;
    }

    public void register(Diary diary) {
        this.diary = diary;
    }

    public boolean isCreator(Long id, Participant participant) {
        return Objects.equals(this.id, id) && this.participant.equals(participant);
    }

    public void update(Content content) {
        this.content = content;
    }

    public String getParticipantAvatar() {
        return participant.getMemberAvatar();
    }

    public Long getId() {
        return id;
    }

    public String getContentValue() {
        return content.getContent();
    }

    public String getParticipantNicknameValue() {
        return participant.getNicknameValue();
    }

    public String getEmail() {
        return participant.getMemberEmail();
    }

    public Long getDiaryIdValue() {
        return diary.getId();
    }
}