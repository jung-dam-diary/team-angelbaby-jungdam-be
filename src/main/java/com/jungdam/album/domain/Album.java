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
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.common.DuplicationException;
import com.jungdam.invitation.domain.Invitation;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import java.util.List;
import java.util.Objects;
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

    public void deleteDiary(Long id, Participant participant) {
        diaries.delete(id, participant);
    }

    public boolean checkRecordedAt(RecordedAt recordedAt, Participant participant) {
        return diaries.isExists(recordedAt, participant);
    }

    public Diary findDiary(Long id) {
        return diaries.findById(id);
    }

    public Participant belong(Member member) {
        return participants.find(member, this);
    }

    public boolean findParticipant(Member member) {
        return participants.find(member);
    }

    public void existsParticipant(Member member) {
        boolean participant = findParticipant(member);
        if (participant) {
            throw new DuplicationException(ErrorMessage.DUPLICATION_PARTICIPANT_IN_ALBUM);
        }
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
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

    public Diary updateDiary(Long id, Participant participant,
        com.jungdam.diary.domain.vo.Title title,
        Content content,
        List<DiaryPhoto> diaryPhotos) {
        Diary diary = diaries.find(id, participant);
        diary.update(title, content, diaryPhotos);

        return diary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Album album = (Album) o;
        return Objects.equals(id, album.id) && Objects.equals(title, album.title)
            && Objects.equals(familyMotto, album.familyMotto) && Objects.equals(
            thumbnail, album.thumbnail) && Objects.equals(participants, album.participants)
            && Objects.equals(invitations, album.invitations) && Objects.equals(
            diaries, album.diaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, familyMotto, thumbnail, participants, invitations, diaries);
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