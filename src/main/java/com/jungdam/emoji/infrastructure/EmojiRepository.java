package com.jungdam.emoji.infrastructure;

import com.jungdam.emoji.domain.Emoji;
import com.jungdam.emoji.domain.vo.Content;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Optional<Emoji> findByContent(Content content);
}