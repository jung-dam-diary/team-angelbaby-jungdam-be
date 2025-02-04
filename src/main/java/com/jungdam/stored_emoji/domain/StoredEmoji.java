package com.jungdam.stored_emoji.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
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

    public StoredEmoji(Emoji emoji, Diary diary, Participant participant) {
        this.emoji = emoji;
        this.diary = diary;
        this.participant = participant;
    }

    public String getEmojiValue() {
        return emoji.getContent().getContent();
    }
  
    public String getParticipantValue() {
        return participant.getNicknameValue();
    }
  
    public boolean isEqual(Emoji emoji) {
        return this.emoji.equals(emoji);
    }


}