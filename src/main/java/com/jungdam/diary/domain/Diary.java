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
import com.jungdam.participant.domain.Participant;
import java.time.LocalDate;
import java.util.List;
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
    @JoinColumn(name = "album_id")
    private Album album;

    @Embedded
    private Comments comments;

    @Embedded
    private DiaryPhotos diaryPhotos;

    @Embedded
    private Emojis emojis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    protected Diary() {
    }

    public Diary(Title title, Content content, RecordedAt recordedAt, Participant participant) {
        this.title = title;
        this.content = content;
        this.recordedAt = recordedAt;
        this.participant = participant;
        this.bookmark = new Bookmark();
        this.diaryPhotos = new DiaryPhotos();
    }

    public static DiaryBuilder builder() {
        return new DiaryBuilder();
    }

    public Long getAlbumValue() {
        return album.getId();
    }

    public String getTitleValue() {
        return title.getTitle();
    }

    public String getContentValue() {
        return content.getContent();
    }

    public boolean getBookmarkValue() {
        return bookmark.getBookmark();
    }

    public List<String> getDiaryPhotosValue() {
        return diaryPhotos.getDiaryPhotosUrl();
    }

    public LocalDate getRecordedAtValue() {
        return recordedAt.getRecordedAt();
    }

    public Album getAlbum() {
        return album;
    }

    public Content getContent() {
        return content;
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

    public void mark() {
        bookmark.mark();
    }

    public void deleteContent(Long id, Participant participant) {
        comments.delete(id, participant);
    }

    public boolean isCreator(Long id, Participant participant) {
        return Objects.equals(this.id, id) && this.participant.equals(participant);
    }

    public boolean isWritten(RecordedAt recordedAt, Participant participant) {
        return this.recordedAt.equals(recordedAt) && this.participant.equals(participant);
    }

    public void updateComment(Long id, Participant participant,
        com.jungdam.comment.domain.vo.Content content) {
        comments.update(id, participant, content);
    }

    public void update(Title title, Content content, List<DiaryPhoto> diaryPhotos) {
        this.title = title;
        this.content = content;
        this.diaryPhotos.removeAll();
        addDiaryPhotos(diaryPhotos);
    }

    public boolean isEquals(Long id) {
        return this.id.equals(id);
    }

    public Long getId() {
        return id;
    }

    public static class DiaryBuilder {

        private Title title;
        private Content content;
        private RecordedAt recordedAt;
        private Participant participant;

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

        public DiaryBuilder participant(final Participant participant) {
            this.participant = participant;
            return this;
        }

        public Diary build() {
            return new Diary(this.title, this.content, this.recordedAt, this.participant);
        }
    }
}