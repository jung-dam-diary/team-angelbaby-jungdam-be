package com.jungdam.participant.domain.vo;

import com.jungdam.emoji.domain.Emoji;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Emojis {

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emoji> emojis = new ArrayList<>();
}