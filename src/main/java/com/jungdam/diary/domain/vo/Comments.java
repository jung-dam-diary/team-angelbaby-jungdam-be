package com.jungdam.diary.domain.vo;

import com.jungdam.comment.domain.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Comments {

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void add(Comment comment) {
        comments.add(comment);
    }
}