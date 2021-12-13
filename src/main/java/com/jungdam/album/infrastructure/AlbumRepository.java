package com.jungdam.album.infrastructure;

import com.jungdam.album.domain.Album;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Override
    Optional<Album> findById(Long id);
}