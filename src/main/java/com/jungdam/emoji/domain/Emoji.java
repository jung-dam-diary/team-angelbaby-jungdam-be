package com.jungdam.emoji.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.emoji.domain.vo.Content;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emoji extends BaseEntity {

    @Id
    @Column(name = "emoji_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    protected Emoji() {

    }

    public Content getContent() {
        return content;
    }

    public String getContentValue() {
        return content.getContent();
    }
}