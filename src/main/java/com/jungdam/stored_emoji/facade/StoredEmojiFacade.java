package com.jungdam.stored_emoji.facade;

import com.jungdam.album.application.AlbumService;
import com.jungdam.album.domain.Album;
import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.application.EmojiService;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.member.application.MemberService;
import com.jungdam.member.domain.Member;
import com.jungdam.participant.domain.Participant;
import com.jungdam.stored_emoji.application.StoredEmojiService;
import com.jungdam.stored_emoji.converter.StoredEmojiConverter;
import com.jungdam.stored_emoji.domain.StoredEmoji;
import com.jungdam.stored_emoji.dto.bundle.CreateAndDeleteStoredEmojiBundle;
import com.jungdam.stored_emoji.dto.bundle.InquireEmojiBundle;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse;
import com.jungdam.stored_emoji.dto.response.CreateAndDeleteStoredEmojiResponse.EmojiDetailResponse;
import com.jungdam.stored_emoji.dto.response.InquireEmojiResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoredEmojiFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final EmojiService emojiService;
    private final StoredEmojiService storedEmojiService;
    private final StoredEmojiConverter storedEmojiConverter;

    public StoredEmojiFacade(MemberService memberService,
        AlbumService albumService, EmojiService emojiService,
        StoredEmojiService storedEmojiService,
        StoredEmojiConverter storedEmojiConverter) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.emojiService = emojiService;
        this.storedEmojiService = storedEmojiService;
        this.storedEmojiConverter = storedEmojiConverter;
    }

    @Transactional
    public CreateAndDeleteStoredEmojiResponse createAndDelete(
        CreateAndDeleteStoredEmojiBundle bundle) {
        Emoji emoji = emojiService.findByContent(bundle.getContent());

        Member member = memberService.findById(bundle.getMemberId());
        Album album = albumService.findById(bundle.getAlbumId());

        Participant participant = album.belong(member);
        Diary diary = album.findDiary(bundle.getDiaryId());

        boolean isExist = storedEmojiService.existsStoredEmoji(diary, participant, emoji);

        if (isExist) {
            StoredEmoji storedEmoji = new StoredEmoji(emoji, diary, participant);
            storedEmojiService.save(storedEmoji);
        } else {
            storedEmojiService.delete(diary, participant, emoji);
        }

        final List<Emoji> emojis = emojiService.findAll();

        final List<StoredEmoji> storedEmojis = storedEmojiService.findByDiary(diary);

        List<EmojiDetailResponse> response = storedEmojiConverter.toEmojiDetailResponses(emojis,
            storedEmojis);

        return storedEmojiConverter.toCreateAndDeleteStoredEmojiResponse(diary, response);
    }

    @Transactional(readOnly = true)
    public InquireEmojiResponse inquire(InquireEmojiBundle bundle) {
        Emoji emoji = emojiService.findByContent(bundle.getContent());

        Member member = memberService.findById(bundle.getMemberId());

        Album album = albumService.findById(bundle.getAlbumId());

        album.belong(member);

        Diary diary = album.findDiary(bundle.getDiaryId());

        List<StoredEmoji> storedEmojis = storedEmojiService.findByDiaryAndEmoji(diary,
            emoji);

        return storedEmojiConverter.toInquireEmojiResponse(emoji, storedEmojis);
    }
}