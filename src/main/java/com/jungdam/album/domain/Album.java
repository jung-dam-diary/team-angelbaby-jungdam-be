package com.jungdam.album.domain;

import com.jungdam.album.domain.vo.Diaries;
import com.jungdam.album.domain.vo.FamilyMotto;
import com.jungdam.album.domain.vo.Invitations;
import com.jungdam.album.domain.vo.Participants;
import com.jungdam.album.domain.vo.Thumbnail;
import com.jungdam.album.domain.vo.Title;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary_photo.domain.DiaryPhoto;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album extends BaseEntity {

    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private FamilyMotto familyMotto;

    @Embedded
    private Thumbnail thumbnail;

    @Embedded
    private Participants participants;

    @Embedded
    private Invitations invitations;

    @Embedded
    private Diaries diaries;

    protected Album() {
    }

    public Album(Title title, FamilyMotto familyMotto, Thumbnail thumbnail) {
        this.title = title;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
        this.participants = new Participants();
    }

    public static AlbumBuilder builder() {
        return new AlbumBuilder();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.register(this);
    }

    public void addDiary(Diary diary) {
        diaries.add(diary);
        diary.register(this);
    }

    public void addInvitation(Invitation invitation) {
        invitations.add(invitation);
        invitation.register(this);
    }

    public void deleteDiary(Long id, Member member) {
        diaries.delete(id, member);
    }

    public boolean checkRecordedAt(RecordedAt recordedAt, Member member) {
        return diaries.isExists(recordedAt, member);
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public FamilyMotto getFamilyMotto() {
        return familyMotto;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getTitleValue() {
        return title.getTitle();
    }

    public String getFamilyMottoValue() {
        return familyMotto.getFamilyMotto();
    }

    public String getThumbnailValue() {
        return thumbnail.getThumbnail();
    }

    public void update(Title title, FamilyMotto familyMotto, Thumbnail thumbnail) {
        this.title = title;
        this.familyMotto = familyMotto;
        this.thumbnail = thumbnail;
    }

    public Diary updateDiary(Long id, Member member, com.jungdam.diary.domain.vo.Title title,
        Content content,
        List<DiaryPhoto> diaryPhotos) {
        Diary diary = diaries.find(id, member);
        diary.update(title, content, diaryPhotos);

        return diary;
    }

    public static class AlbumBuilder {

        private Long id;
        private Title title;
        private FamilyMotto familyMotto;
        private Thumbnail thumbnail;

        private AlbumBuilder() {
        }

        public AlbumBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public AlbumBuilder title(final Title title) {
            this.title = title;
            return this;
        }

        public AlbumBuilder familyMotto(final FamilyMotto familyMotto) {
            this.familyMotto = familyMotto;
            return this;
        }

        public AlbumBuilder thumbnail(final Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Album build() {
            return new Album(this.title, this.familyMotto, this.thumbnail);
        }
    }
}