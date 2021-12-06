package com.jungdam.diary.domain.vo;

import com.jungdam.emoji.domain.Emoji;
import java.util.ArrayList;
import java.util.List;
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
}