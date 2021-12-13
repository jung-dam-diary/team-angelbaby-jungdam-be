package com.jungdam.diary.domain;

import com.jungdam.album.domain.Album;
import com.jungdam.comment.domain.Comment;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.vo.Bookmark;
import com.jungdam.diary.domain.vo.Comments;
import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.DiaryPhotos;
import com.jungdam.diary.domain.vo.Emojis;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.domain.vo.Title;
import com.jungdam.diary_photo.domain.DiaryPhoto;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.member.domain.Member;
import java.util.List;
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
public class Diary extends BaseEntity {

    @Id
    @Column(name = "diary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private Bookmark bookmark;

    @Embedded
    private RecordedAt recordedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @Embedded
    private Comments comments;

    @Embedded
    private DiaryPhotos diaryPhotos;

    @Embedded
    private Emojis emojis;

    protected Diary() {

    }

    public Diary(Title title, Content content, RecordedAt recordedAt, Member member) {
        this.title = title;
        this.content = content;
        this.recordedAt = recordedAt;
        this.member = member;
        this.bookmark = new Bookmark();
        this.diaryPhotos = new DiaryPhotos();
    }

    public static DiaryBuilder builder() {
        return new DiaryBuilder();
    }

    public void register(Album album) {
        this.album = album;
    }

    public void addDiaryPhotos(List<DiaryPhoto> photos) {
        photos.forEach(this::addDiaryPhoto);
    }

    private void addDiaryPhoto(DiaryPhoto diaryPhoto) {
        diaryPhotos.add(diaryPhoto);
        diaryPhoto.register(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.register(this);
    }

    public void addEmoji(Emoji emoji) {
        emojis.add(emoji);
        emoji.register(this);
    }

    public Long getId() {
        return id;
    }

    public static class DiaryBuilder {

        private Title title;
        private Content content;
        private RecordedAt recordedAt;
        private Member member;

        private DiaryBuilder() {

        }

        public DiaryBuilder title(final Title title) {
            this.title = title;
            return this;
        }

        public DiaryBuilder content(final Content content) {
            this.content = content;
            return this;
        }

        public DiaryBuilder recordedAt(final RecordedAt recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public DiaryBuilder member(final Member member) {
            this.member = member;
            return this;
        }

        public Diary build() {
            return new Diary(this.title, this.content, this.recordedAt, this.member);
        }
    }
}