package com.jungdam.album.presentation;

import com.jungdam.album.dto.bundle.CreateAlbumBundle;
import com.jungdam.album.dto.bundle.DeleteAlbumBundle;
import com.jungdam.album.dto.bundle.ReadAllMomentBundle;
import com.jungdam.album.dto.bundle.ReadOneAlbumBundle;
import com.jungdam.album.dto.bundle.UpdateAlbumBundle;
import com.jungdam.album.dto.request.CreateAlbumRequest;
import com.jungdam.album.dto.request.UpdateAlbumRequest;
import com.jungdam.album.dto.response.CreateAlbumResponse;
import com.jungdam.album.dto.response.DeleteAlbumResponse;
import com.jungdam.album.dto.response.ReadAllMomentResponse;
import com.jungdam.album.dto.response.ReadOneAlbumResponse;
import com.jungdam.album.dto.response.UpdateAlbumResponse;
import com.jungdam.album.facade.AlbumFacade;
import com.jungdam.common.dto.ResponseDto;
import com.jungdam.common.dto.ResponseMessage;
import com.jungdam.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("Albums")
@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private static final int DEFAULT_SIZE = 10;

    private final AlbumFacade albumFacade;

    public AlbumController(AlbumFacade albumFacade) {
        this.albumFacade = albumFacade;
    }

    @ApiOperation("앨범 생성")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateAlbumResponse>> create(
        @RequestBody CreateAlbumRequest request) {
        Long memberId = SecurityUtils.getCurrentUsername();

        CreateAlbumBundle bundle = new CreateAlbumBundle(memberId, request);
        CreateAlbumResponse response = albumFacade.insert(bundle);

        return ResponseDto.of(ResponseMessage.ALBUM_CREATE_SUCCESS, response);
    }

    @ApiOperation("앨범 제목 및 가훈 조회")
    @GetMapping("/{albumId}")
    public ResponseEntity<ResponseDto<ReadOneAlbumResponse>> getOne(@PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        ReadOneAlbumBundle bundle = new ReadOneAlbumBundle(memberId, albumId);

        ReadOneAlbumResponse response = albumFacade.find(bundle);

        return ResponseDto.of(ResponseMessage.ALBUM_READ_SUCCESS, response);
    }

    @ApiOperation("특별한 순간 조회")
    @GetMapping("/{albumId}/moments")
    public ResponseEntity<ResponseDto<ReadAllMomentResponse>> getMoments(
        @PathVariable final Long albumId,
        @RequestParam(value = "cursorId", required = false) Long cursorId,
        @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (Objects.isNull(pageSize)) {
            pageSize = DEFAULT_SIZE;
        }

        Long memberId = SecurityUtils.getCurrentUsername();

        ReadAllMomentBundle bundle = new ReadAllMomentBundle(memberId, albumId, cursorId, pageSize);

        ReadAllMomentResponse response = albumFacade.findAllMoment(bundle);

        return ResponseDto.of(ResponseMessage.MOMENT_READ_ALL_SUCCESS, response);
    }

    @ApiOperation("앨범 수정")
    @PutMapping("/{albumId}")
    public ResponseEntity<ResponseDto<UpdateAlbumResponse>> update(@PathVariable Long albumId,
        @RequestBody UpdateAlbumRequest request) {

        Long memberId = SecurityUtils.getCurrentUsername();

        UpdateAlbumBundle bundle = new UpdateAlbumBundle(memberId, albumId, request);

        UpdateAlbumResponse response = albumFacade.update(bundle);

        return ResponseDto.of(ResponseMessage.ALBUM_UPDATE_SUCCESS, response);
    }

    @ApiOperation("앨범 삭제")
    @DeleteMapping("/{albumId}")
    public ResponseEntity<ResponseDto<DeleteAlbumResponse>> delete(@PathVariable Long albumId) {
        Long memberId = SecurityUtils.getCurrentUsername();

        DeleteAlbumBundle bundle = new DeleteAlbumBundle(memberId, albumId);

        DeleteAlbumResponse response = albumFacade.delete(bundle);

        return ResponseDto.of(ResponseMessage.ALBUM_DELETE_SUCCESS, response);
    }
}