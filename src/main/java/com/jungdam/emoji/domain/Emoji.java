package com.jungdam.emoji.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.domain.vo.Content;
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
public class Emoji extends BaseEntity {

    @Id
    @Column(name = "emoji_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;
    
    protected Emoji() {
    }

    public void register(Diary diary) {
        this.diary = diary;
    }
}