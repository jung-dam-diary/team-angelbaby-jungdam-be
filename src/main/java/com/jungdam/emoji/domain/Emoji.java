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

    public Emoji(Content content, Participant participant, Diary diary) {
        this.content = content;
        this.participant = participant;
        this.diary = diary;
    }

    public static EmojiBuilder builder() {
        return new EmojiBuilder();
    }

    public void register(Diary diary) {
        this.diary = diary;
    }

    public boolean isEquals(Participant participant, Content content) {
        return this.content.equals(content) && this.participant.equals(participant);
    }

    public static class EmojiBuilder {

        private Content content;
        private Participant participant;
        private Diary diary;

        protected EmojiBuilder() {
        }

        public EmojiBuilder content(final Content content) {
            this.content = content;
            return this;
        }

        public EmojiBuilder participant(final Participant participant) {
            this.participant = participant;
            return this;
        }

        public EmojiBuilder diary(final Diary diary) {
            this.diary = diary;
            return this;
        }

        public Emoji build() {
            return new Emoji(this.content, this.participant, this.diary);
        }
    }
}