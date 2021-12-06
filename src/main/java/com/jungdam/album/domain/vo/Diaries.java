package com.jungdam.album.domain.vo;

import com.jungdam.diary.domain.Diary;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Diaries {

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

    public void add(Diary diary) {
        diaries.add(diary);
    }
}