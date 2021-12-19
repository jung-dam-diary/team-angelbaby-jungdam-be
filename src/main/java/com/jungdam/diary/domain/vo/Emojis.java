package com.jungdam.diary.domain.vo;

import com.jungdam.diary.domain.Diary;
import com.jungdam.emoji.domain.Emoji;
import com.jungdam.emoji.domain.vo.Content;
import com.jungdam.participant.domain.Participant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Emojis {

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emoji> emojis = new ArrayList<>();

    public void add(Emoji emoji) {
        emojis.add(emoji);
    }

    public void remove(Emoji emoji) {
        emojis.remove(emoji);
    }

    public boolean register(Participant participant, Diary diary, Content content) {
        final Emoji emoji = find(participant, content);
        if (Objects.isNull(emoji)) {
            Emoji instance = Emoji.builder()
                .content(content)
                .participant(participant)
                .diary(diary)
                .build();
            add(instance);
            return true;
        }
        remove(emoji);
        return false;
    }

    public Emoji find(Participant participant, Content content) {
        return emojis.stream()
            .filter(e -> e.isEquals(participant, content))
            .findFirst()
            .orElse(null);
    }
}