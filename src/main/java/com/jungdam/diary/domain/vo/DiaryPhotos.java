package com.jungdam.diary.domain.vo;

import com.jungdam.diary_photo.domain.DiaryPhoto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class DiaryPhotos {

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaryPhoto> diaryPhotos = new ArrayList<>();

    public DiaryPhotos() {
    }

    public void add(DiaryPhoto diaryPhoto) {
        diaryPhotos.add(diaryPhoto);
    }

    public List<String> getDiaryPhotosUrl() {
        return diaryPhotos.stream()
            .map(DiaryPhoto::getImageValue)
            .collect(Collectors.toList());
    }

    public void removeAll() {
        diaryPhotos.clear();
    }
}