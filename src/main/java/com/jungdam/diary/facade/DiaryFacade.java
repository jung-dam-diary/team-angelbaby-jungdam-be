package com.jungdam.diary.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.diary.application.DiaryService;
import com.jungdam.diary.converter.DiaryConverter;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.dto.bundle.CheckBookmarkBundle;
import com.jungdam.diary.dto.bundle.CheckRecordedAtDiaryBundle;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.bundle.DeleteDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadAllDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadDiaryBundle;
import com.jungdam.diary.dto.bundle.UpdateDiaryBundle;
import com.jungdam.diary.dto.response.CheckBookmarkResponse;
import com.jungdam.diary.dto.response.CheckRecordedAtDiaryResponse;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.dto.response.DeleteDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllFeedDiaryResponse;
import com.jungdam.diary.dto.response.ReadDetailDiaryResponse;
import com.jungdam.diary.dto.response.UpdateDiaryResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final DiaryService diaryService;
    private final DiaryConverter diaryConverter;

    public DiaryFacade(MemberService memberService,
        AlbumService albumService, DiaryService diaryService,
        DiaryConverter diaryConverter) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.diaryService = diaryService;
        this.diaryConverter = diaryConverter;
    }

    @Transactional
    public CreateDiaryResponse insert(CreateDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        Participant participant = album.belong(member);

        Diary diary = diaryService.save(bundle, participant);

        album.addDiary(diary);

        return diaryConverter.toCreateDiaryResponse(diary);
    }

    @Transactional(readOnly = true)
    public ReadDetailDiaryResponse find(ReadDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        album.belong(member);

        Diary diary = album.findDiary(bundle.getDiaryId());
        return diaryConverter.toReadDiaryResponse(member.getEmail(), diary);
    }

    @Transactional
    public CheckBookmarkResponse mark(CheckBookmarkBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        album.belong(member);

        Diary diary = album.findDiary(bundle.getDiaryId());
        diary.mark();

        return diaryConverter.toCheckBookmarkResponse(diary);
    }

    @Transactional
    public DeleteDiaryResponse delete(DeleteDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        Participant participant = album.belong(member);

        album.deleteDiary(bundle.getDiaryId(), participant);

        return diaryConverter.toDeleteDiaryResponse(album);
    }

    @Transactional(readOnly = true)
    public CheckRecordedAtDiaryResponse checkRecordedAt(CheckRecordedAtDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        Participant participant = album.belong(member);

        boolean existDiaryStatus = album.checkRecordedAt(bundle.getRecordedAt(), participant);

        return diaryConverter.toCheckRecordedAtDiaryResponse(album, bundle.getRecordedAt(),
            existDiaryStatus);
    }

    @Transactional
    public UpdateDiaryResponse update(UpdateDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        Participant participant = album.belong(member);

        Diary diary = album.updateDiary(bundle.getDiaryId(), participant, bundle.getTitle(),
            bundle.getContent(),
            bundle.getDiaryPhotos());

        return diaryConverter.toUpdateDiaryResponse(diary);
    }

    @Transactional
    public ReadAllFeedDiaryResponse findAll(ReadAllDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        album.belong(member);

        return diaryService.findAllFeed(album, bundle.getCursorId(), bundle.getPageSize());
    }
}