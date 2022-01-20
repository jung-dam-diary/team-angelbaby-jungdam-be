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
import com.jungdam.stored_emoji.domain.StoredEmoji;
import com.jungdam.stored_emoji.dto.bundle.CreateAndDeleteStoredEmojiBundle;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoredEmojiFacade {

    private final MemberService memberService;
    private final AlbumService albumService;
    private final EmojiService emojiService;
    private final StoredEmojiService storedEmojiService;

    public StoredEmojiFacade(MemberService memberService,
        AlbumService albumService, EmojiService emojiService,
        StoredEmojiService storedEmojiService) {
        this.memberService = memberService;
        this.albumService = albumService;
        this.emojiService = emojiService;
        this.storedEmojiService = storedEmojiService;
    }

    @Transactional
    public void createAndDelete(CreateAndDeleteStoredEmojiBundle bundle) {
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

        final List<StoredEmoji> byDiary = storedEmojiService.findByDiary(diary);

        // TODO CONVERTING을 통한 RESPONSE 제공
    }
}