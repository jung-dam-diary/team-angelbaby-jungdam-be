package com.jungdam.comment.infrastructure;

import com.jungdam.comment.domain.Comment;
import com.jungdam.diary.domain.Diary;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    <S extends Comment> S save(S entity);

    List<Comment> findAllByDiaryOrderByIdDesc(Diary diary,
        Pageable page);

    List<Comment> findAllByDiaryAndIdLessThanOrderByIdDesc(
        Diary diary,
        Long id, Pageable page);
}