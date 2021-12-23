package com.jungdam.diary.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.common.utils.PageUtils;
import com.jungdam.diary.application.DiaryService;
import com.jungdam.diary.converter.DiaryConverter;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.dto.bundle.CheckBookmarkBundle;
import com.jungdam.diary.dto.bundle.CheckRecordedAtDiaryBundle;
import com.jungdam.diary.dto.bundle.CreateDiaryBundle;
import com.jungdam.diary.dto.bundle.DeleteDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadAllDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadAllStoryBookBundle;
import com.jungdam.diary.dto.bundle.ReadDiaryBundle;
import com.jungdam.diary.dto.bundle.ReadGroupStoryBookBundle;
import com.jungdam.diary.dto.bundle.UpdateDiaryBundle;
import com.jungdam.diary.dto.response.CheckBookmarkResponse;
import com.jungdam.diary.dto.response.CheckRecordedAtDiaryResponse;
import com.jungdam.diary.dto.response.CreateDiaryResponse;
import com.jungdam.diary.dto.response.DeleteDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllFeedDiaryResponse;
import com.jungdam.diary.dto.response.ReadAllStoryBookResponse;
import com.jungdam.diary.dto.response.ReadDetailDiaryResponse;
import com.jungdam.diary.dto.response.ReadGroupStoryBookResponse;
import com.jungdam.diary.dto.response.UpdateDiaryResponse;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.application.ParticipantService;
import com.jungdam.participant.domain.Participant;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final DiaryService diaryService;
    private final DiaryConverter diaryConverter;
    private final ParticipantService participantService;

    public DiaryFacade(MemberService memberService,
        AlbumService albumService, DiaryService diaryService,
        DiaryConverter diaryConverter,
        ParticipantService participantService) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.diaryService = diaryService;
        this.diaryConverter = diaryConverter;
        this.participantService = participantService;
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
        return diaryConverter.toReadDiaryResponse(diary);
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
    public ReadAllFeedDiaryResponse findAllFeed(ReadAllDiaryBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());
        Member member = memberService.findById(bundle.getMemberId());

        album.belong(member);

        Pageable page = PageUtils.of(bundle.getPageSize());

        return diaryService.findAllFeed(album, bundle.getCursorId(), page);
    }

    //TODO 조회하는 회원이 앨범 참여자인지 검증하는 로직 추가 필요
    @Transactional(readOnly = true)
    public ReadAllStoryBookResponse findAllStoryBook(ReadAllStoryBookBundle bundle) {
        Album album = albumService.findById(bundle.getAlbumId());

        // Participants에 findById 만들어두었습니당~
        Participant participant = participantService.findById(bundle.getParticipantId());

        Pageable page = PageUtils.of(bundle.getPageSize());

        return diaryService.findAllStoryBook(album, participant, bundle.getCursorId(), page);
    }

    @Transactional(readOnly = true)
    public List<ReadGroupStoryBookResponse> findStoryBook(ReadGroupStoryBookBundle bundle) {
        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        // album.belong() 메서드가 있습니당!
        participantService.checkExists(album, member);

        List<Participant> participants = participantService.findAllByAlbum(album);
        Pageable page = PageRequest.of(0, 4); //TODO 나중에 상수로 분리
        return diaryService.findStoryBook(album, participants, page);
    }
}