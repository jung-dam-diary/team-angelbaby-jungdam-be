package com.jungdam.diary.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.participant.domain.Participant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StoredEmoji extends BaseEntity {

    @Id
    @Column(name = "stored_emoji_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    protected StoredEmoji() {

    }
}