package com.jungdam.diary_image.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import javax.persistence.Column;
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
    @Column(name = "diary_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diary_photo_image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;
    
    protected DiaryPhoto() {

    }

    public void register(Diary diary) {
        this.diary = diary;
    }
}