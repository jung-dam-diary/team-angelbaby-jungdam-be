package com.jungdam.album.application;

import com.jungdam.album.domain.Album;
import com.jungdam.album.infrastructure.AlbumRepository;
import com.jungdam.error.ErrorMessage;
import com.jungdam.error.exception.NotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional(readOnly = true)
    public Album findById(Long id) {
        return albumRepository.findById(id)
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_ALBUM));
    }
}