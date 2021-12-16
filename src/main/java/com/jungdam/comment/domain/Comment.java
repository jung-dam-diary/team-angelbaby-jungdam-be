package com.jungdam.comment.domain;

import com.jungdam.comment.domain.vo.Content;
import com.jungdam.common.domain.BaseEntity;
import com.jungdam.diary.domain.Diary;
import com.jungdam.member.domain.Member;
import java.util.Objects;
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
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    protected Comment() {
    }

    public Comment(Content content, Member member) {
        this.content = content;
        this.member = member;
    }

    public void register(Diary diary) {
        this.diary = diary;
    }

    public boolean isCreator(Long id, Member member) {
        return Objects.equals(this.id, id) && this.member.equals(member);
    }

    public Long getId() {
        return id;
    }

    public String getContentValue() {
        return content.getContent();
    }
}