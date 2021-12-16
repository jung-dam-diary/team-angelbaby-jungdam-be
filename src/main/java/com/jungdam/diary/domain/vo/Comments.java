package com.jungdam.diary.domain.vo;

import com.jungdam.comment.domain.Comment;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import com.jungdam.member.domain.Member;
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

    public void remove(Long id, Member member) {
        for (Comment cmt : comments) {
            if (cmt.isCreator(id, member)) {
                comments.remove(cmt);
                return;
            }
        }
        throw new NotExistException(ErrorMessage.NOT_EXIST_COMMENT);
    }
}