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
import com.jungdam.diary.dto.bundle.ReadDiaryBundle;
import com.jungdam.diary.dto.response.CheckBookmarkResponse;
import com.jungdam.diary.dto.response.CheckRecordedAtDiaryResponse;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.dto.response.DeleteDiaryResponse;
import com.jungdam.diary.dto.response.ReadDiaryResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final DiaryService diaryService;
    private final ParticipantService participantService;
    private final DiaryConverter diaryConverter;

    public DiaryFacade(MemberService memberService,
        AlbumService albumService, DiaryService diaryService,
        ParticipantService participantService, DiaryConverter diaryConverter) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.diaryService = diaryService;
        this.participantService = participantService;
        this.diaryConverter = diaryConverter;
    }

    @Transactional
    public CreateDiaryResponse insert(CreateDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkNotExists(album, member);

        Diary diary = diaryService.save(bundle, member);

        album.addDiary(diary);

        return diaryConverter.toCreateDiaryResponse(diary);
    }

    @Transactional(readOnly = true)
    public ReadDiaryResponse find(ReadDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkNotExists(album, member);

        Diary diary = diaryService.findById(bundle.getDiaryId());

        return diaryConverter.toReadDiaryResponse(diary);
    }

    @Transactional
    public CheckBookmarkResponse mark(CheckBookmarkBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkNotExists(album, member);

        Diary diary = diaryService.findById(bundle.getDiaryId());
        diary.mark();

        return diaryConverter.toCheckBookmarkResponse(diary);
    }

    @Transactional
    public DeleteDiaryResponse delete(DeleteDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkNotExists(album, member);

        album.deleteDiary(bundle.getDiaryId(), member);

        return diaryConverter.toDeleteDiaryResponse(album);
    }

    @Transactional(readOnly = true)
    public CheckRecordedAtDiaryResponse checkRecordedAt(CheckRecordedAtDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        participantService.checkNotExists(album, member);

        boolean existDiaryStatus = album.checkRecordedAt(bundle.getRecordedAt(), member);

        return diaryConverter.toCheckRecordedAtDiaryResponse(album, bundle.getRecordedAt(),
            existDiaryStatus);
    }
}