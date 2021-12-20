package com.jungdam.diary.converter;

import com.jungdam.album.domain.Album;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.response.CheckBookmarkResponse;
import com.jungdam.diary.dto.response.CheckRecordedAtDiaryResponse;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.dto.response.DeleteDiaryResponse;
import com.jungdam.diary.dto.response.DiaryInfoResponse;
import com.jungdam.diary.dto.response.ReadAllFeedDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllStoryBookResponse;
import com.jungdam.diary.dto.response.ReadDetailDiaryResponse;
import com.jungdam.diary.dto.response.ReadFeedDiaryResponse;
import com.jungdam.diary.dto.response.ReadGroupStoryBookResponse;
import com.jungdam.diary.dto.response.ReadStoryBookResponse;
import com.jungdam.diary.dto.response.UpdateDiaryResponse;
import com.jungdam.member.domain.vo.Email;
import com.jungdam.participant.domain.Participant;
import com.jungdam.participant.dto.response.ParticipantInfoResponse;
import com.jungdam.participant.dto.response.ParticipantInfosResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DiaryConverter {

    public CheckBookmarkResponse toCheckBookmarkResponse(Diary diary) {
        return new CheckBookmarkResponse(diary.getId(), diary.getBookmarkValue());
    }

    public CreateDiaryResponse toCreateDiaryResponse(Diary diary) {
        return new CreateDiaryResponse(diary.getAlbumValue(), diary.getId());
    }

    public ReadDetailDiaryResponse toReadDiaryResponse(Email email, Diary diary) {
        ParticipantInfosResponse participant = ParticipantInfosResponse.builder()
            .email(email.getEmail())
            .nickname(diary.getNicknameValue())
            .avatar(diary.getAvatarValue())
            .build();

        return ReadDetailDiaryResponse.builder()
            .albumId(diary.getAlbumValue())
            .diaryId(diary.getId())
            .title(diary.getTitleValue())
            .content(diary.getContentValue())
            .bookmark(diary.getBookmarkValue())
            .diaryPhotos(diary.getDiaryPhotosValue())
            .recordedAt(diary.getRecordedAtValue())
            .info(participant)
            .build();
    }

    public Diary toDiary(CreateDiaryBundle bundle, Participant participant) {
        return Diary.builder()
            .title(bundle.getTitle())
            .content(bundle.getContent())
            .recordedAt(bundle.getRecordedAt())
            .participant(participant)
            .build();
    }

    public DeleteDiaryResponse toDeleteDiaryResponse(Album album) {
        return new DeleteDiaryResponse(album.getId());
    }

    public CheckRecordedAtDiaryResponse toCheckRecordedAtDiaryResponse(Album album,
        RecordedAt recordedAt,
        boolean existence) {
        return CheckRecordedAtDiaryResponse.builder()
            .albumId(album.getId())
            .recordedAt(recordedAt.getRecordedAt())
            .existence(existence)
            .build();
    }

    public UpdateDiaryResponse toUpdateDiaryResponse(Diary diary) {
        return new UpdateDiaryResponse(diary.getAlbumValue(), diary.getId());
    }

    public ReadAllFeedDiaryResponse toReadAllFeedDiaryResponse(boolean hasNext,
        String nextRecordedAt, List<Diary> diaries) {
        List<ReadFeedDiaryResponse> all = diaries.stream()
            .map(d ->
                new ReadFeedDiaryResponse(
                    DiaryInfoResponse.builder()
                        .diaryId(d.getId())
                        .title(d.getTitleValue())
                        .bookmark(d.getBookmarkValue())
                        .diaryPhotos(d.getDiaryPhotosValue())
                        .recordedAt(d.getRecordedAtValue())
                        .build(),
                    new ParticipantInfoResponse(d.getNicknameValue(), d.getAvatarValue())
                ))
            .collect(Collectors.toList());

        return ReadAllFeedDiaryResponse.builder()
            .hasNext(hasNext)
            .lastCommentId(nextRecordedAt)
            .diaries(all)
            .build();
    }

    public ReadAllStoryBookResponse toReadAllStoryBookResponse(Boolean hasNext, Participant participant,
        List<Diary> diaries) {
        List<ReadStoryBookResponse> all = diaries.stream()
            .map(this::toReadStoryBookResponse)
            .collect(Collectors.toList());

        return ReadAllStoryBookResponse.builder()
            .hasNext(hasNext)
            .participantId(participant.getId())
            .participantNickname(participant.getNicknameValue())
            .participantAvatar(participant.getMemberAvatar())
            .diaries(all)
            .build();
    }

    public ReadStoryBookResponse toReadStoryBookResponse(Diary diary) {
        return ReadStoryBookResponse.builder()
            .id(diary.getId())
            .title(diary.getTitleValue())
            .recordedAt(diary.getRecordedAtValue())
            .photo(diary.getDiaryPhotoValue())
            .build();
    }

    public ReadGroupStoryBookResponse toReadParticipantStoryBookResponse(Participant participant,
        List<Diary> diaries) {
        List<ReadStoryBookResponse> all = diaries.stream()
            .map(this::toReadStoryBookResponse)
            .collect(Collectors.toList());

        return ReadGroupStoryBookResponse.builder()
            .participantId(participant.getId())
            .participantNickname(participant.getNicknameValue())
            .participantAvatar(participant.getMemberAvatar())
            .diaries(all)
            .build();
    }
}