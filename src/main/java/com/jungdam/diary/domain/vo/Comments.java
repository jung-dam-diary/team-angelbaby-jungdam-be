package com.jungdam.diary.domain.vo;

import com.jungdam.comment.domain.Comment;
import com.jungdam.comment.domain.vo.Content;
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

    public void update(Long id, Member member, Content content) {
        Comment comment = find(id, member);
        comment.update(content);
    }

    public void delete(Long id, Member member) {
        Comment comment = find(id, member);
        remove(comment);
    }

    private Comment find(Long id, Member member) {
        return comments.stream()
            .filter(c -> c.isCreator(id, member))
            .findFirst()
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_COMMENT));
    }

    private void remove(Comment comment) {
        comments.remove(comment);
    }
}