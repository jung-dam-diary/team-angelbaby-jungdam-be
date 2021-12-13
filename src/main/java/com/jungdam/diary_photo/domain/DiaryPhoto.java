package com.jungdam.diary_photo.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.diary_photo.domain.vo.Image;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DiaryPhoto extends BaseEntity {

    @Id
    @Column(name = "diary_photo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    protected DiaryPhoto() {

    }

    public DiaryPhoto(Image image) {
        this.image = image;
    }

    public void register(Diary diary) {
        this.diary = diary;
    }

    public Image getImage() {
        return image;
    }
}