package com.jungdam.diary.domain.vo;

import com.jungdam.comment.domain.Comment;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.participant.domain.Participant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Comments {

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Comment find(Long id, Participant participant) {
        return comments.stream()
            .filter(c -> c.isCreator(id, participant))
            .findFirst()
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_COMMENT));
    }

    public void add(Comment comment) {
        comments.add(comment);
    }

    public void delete(Long id, Participant participant) {
        Comment comment = find(id, participant);
        remove(comment);
    }

    private void remove(Comment comment) {
        comments.remove(comment);
    }
}